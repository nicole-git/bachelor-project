package app;

import app.controller.ExerciseController;
import app.model.CodeRunningJob;
import app.model.Exercise;
import app.model.LanguageViewModel;
import app.util.FirebaseUtil;
import app.util.ScriptService;
import com.google.firebase.database.FirebaseDatabase;
import app.exception.NotFoundException;
import io.javalin.Javalin;

import static io.javalin.ApiBuilder.get;
import static io.javalin.ApiBuilder.path;
import static io.javalin.ApiBuilder.post;
import static io.javalin.translator.template.TemplateUtil.model;

public class Main {

    public static FirebaseDatabase firebaseDatabase = FirebaseUtil.initFirebase();

    public static void main(String[] args) throws Exception {

        Javalin app = Javalin.create()
                .port(7000)
                .enableStaticFiles("/public")
                .start();

        app.routes(() -> {

            get("/", ctx -> ctx.redirect("/exercises"));

            get("/exercises", ctx -> ctx.renderVelocity("/velocity/exercises.vm"));

            get("/about", ctx -> ctx.renderVelocity("/velocity/about.vm"));

            get("/exercises/:exercise-id", ctx -> { // one specific exercise, get by id
                String exerciseId = ctx.param("exercise-id");
                ctx.renderVelocity("/velocity/exercise.vm", model(
                        "supportedLanguages", LanguageViewModel.supportedLanguages,
                        "exercise", ExerciseController.getExercise(exerciseId)
                ));
            });

            path("/api", () -> {

                get("/exercises", ctx -> {
                    ctx.json(ExerciseController.getAllExercises());
                });

                post("/run-code", ctx -> { // just run the user code (Run code)
                    CodeRunningJob input = ctx.bodyAsClass(CodeRunningJob.class); // convert post-body to class
                    String result = (ScriptService.runScript(input.language, input.code));
                    ctx.json(result); // send runScript result to client, as json
                });

                post("/run-code-with-test", ctx -> { // run user code and test if correct (Check answer)
                    CodeRunningJob input = ctx.bodyAsClass(CodeRunningJob.class);
                    Exercise exercise = ExerciseController.getExercise(input.exerciseId); //gets the exercise the user is solving
                    String result = (ScriptService.runScriptWithTest(input.language, input.code, exercise.testCode));
                    ctx.json(result); // send runScriptWithTest result to client, as json
                });

            });

        });

        app.exception(NotFoundException.class, (exception, ctx) -> ctx.status(404));
        app.error(404, ctx -> ctx.renderVelocity("/velocity/notFound.vm"));

    }

}
