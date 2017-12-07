package app;

import app.controller.ExerciseController;
import app.controller.UserController;
import app.exception.NotFoundException;
import app.model.CodeRunningJob;
import app.model.Exercise;
import app.model.LanguageViewModel;
import app.security.MyRole;
import app.util.FirebaseUtil;
import app.util.ScriptService;
import com.google.firebase.database.FirebaseDatabase;
import io.javalin.Javalin;
import static app.security.MyRole.LOGGED_IN;
import static io.javalin.ApiBuilder.get;
import static io.javalin.ApiBuilder.path;
import static io.javalin.ApiBuilder.post;
import static io.javalin.security.Role.roles;
import static io.javalin.translator.template.TemplateUtil.model;

public class Main {

    public static FirebaseDatabase firebaseDatabase = FirebaseUtil.initFirebase();

    public static void main(String[] args) throws Exception {

        Javalin app = Javalin.create()
            .port(7000)
            .enableStaticFiles("/public")
            .accessManager((handler, ctx, permittedRoles) -> {
                MyRole userRole = MyRole.getRole(ctx);
                if (permittedRoles.contains(userRole)) {
                    handler.handle(ctx);
                } else {
                    ctx.renderVelocity("/velocity/login.vm");
                }
            })
            .start();

        app.routes(() -> {

            post("/login", ctx -> {
                //todo: this has to be fixed
                if ("user1".equals(ctx.formParam("username")) && "password".equals(ctx.formParam("password"))) {
                    ctx.sessionAttribute("logged_in", true);
                    ctx.redirect("/");
                } else {
                    ctx.renderVelocity("/velocity/login.vm");
                }
            });

            get("/logout", ctx -> {
                ctx.sessionAttribute("logged_in", false);
                ctx.redirect("/");
            });

            get("/", ctx -> ctx.redirect("/exercises"), roles(LOGGED_IN));

            get("/exercises", ctx -> ctx.renderVelocity("/velocity/exercises.vm"), roles(LOGGED_IN));

            get("/about", ctx -> ctx.renderVelocity("/velocity/about.vm"), roles(LOGGED_IN));

            get("/statistics/:user-id", ctx -> {
                ctx.renderVelocity("/velocity/statistics.vm", model(
                    "solvedExercises", UserController.getUserInfoByUserId("user1").solvedExercises.values().stream().filter(value -> value == true).count(),
                    "totalExercises", ExerciseController.getAllExercises().size()
                ));
            }, roles(LOGGED_IN));

            get("/exercises/:exercise-id", ctx -> { // one specific exercise, get by id
                String exerciseId = ctx.param("exercise-id");
                ctx.renderVelocity("/velocity/exercise.vm", model(
                    "supportedLanguages", LanguageViewModel.supportedLanguages,
                    "exercise", ExerciseController.getExercise(exerciseId)
                ));
            }, roles(LOGGED_IN));

            path("/api", () -> {

                get("/exercises", ctx -> {
                    ctx.json(ExerciseController.getExerciseVms());
                }, roles(LOGGED_IN));

                post("/run-code", ctx -> { // just run the user code (Run code)
                    CodeRunningJob input = ctx.bodyAsClass(CodeRunningJob.class); // convert post-body to class
                    String result = (ScriptService.runScript(input.language, input.code));
                    ctx.json(result); // send runScript result to client, as json
                }, roles(LOGGED_IN));

                post("/run-code-with-test", ctx -> { // run user code and test if correct (Check answer)
                    CodeRunningJob input = ctx.bodyAsClass(CodeRunningJob.class);
                    Exercise exercise = ExerciseController.getExercise(input.exerciseId); //gets the exercise the user is solving
                    String result = (ScriptService.runScriptWithTest(input.language, input.code, exercise.testCode));
                    if ("Your solution is correct, good job!".equals(result)) { // todo: fix this
                        UserController.setExerciseSolved("user1", exercise.id);
                    }
                    ctx.json(result); // send runScriptWithTest result to client, as json
                }, roles(LOGGED_IN));

            });

        });

        app.exception(NotFoundException.class, (exception, ctx) -> ctx.status(404));
        app.error(404, ctx -> ctx.renderVelocity("/velocity/notFound.vm"));

        ExerciseController.getAllExercises(); // connect to firebase

        // set exercise 1 to not-solved every time you start the server
        FirebaseUtil.synchronizeWrite("userinfo/user1/solvedExercises/exercise-1", false);

    }

}
