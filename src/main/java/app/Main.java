package app;

import app.controller.*;
import app.exception.NotFoundException;
import app.model.*;
import app.security.UserRole;
import app.util.FakeDataUtil;
import app.util.FirebaseUtil;
import app.util.ScriptService;
import app.util.ViewUtil;
import app.viewmodel.LanguageVm;
import com.google.firebase.database.FirebaseDatabase;
import io.javalin.Javalin;

import static app.security.UserRole.STUDENT;
import static app.security.UserRole.TEACHER;
import static io.javalin.ApiBuilder.*;
import static io.javalin.security.Role.roles;
import static io.javalin.translator.template.TemplateUtil.model;

public class Main {

    public static FirebaseDatabase firebaseDatabase = FirebaseUtil.initFirebase();

    public static void main(String[] args) {

        Javalin app = Javalin.create()
                .port(7000)
                .enableRouteOverview("/routes")
                .enableStaticFiles("/public")
                .accessManager((handler, ctx, permittedRoles) -> {
                    UserRole userRole = UserRole.getRole(ctx);
                    if (permittedRoles.contains(userRole)) {
                        handler.handle(ctx);
                    } else {
                        ViewUtil.renderToCtx(ctx, "/velocity/login.vm");
                    }
                })
                .start();

        app.routes(() -> {

            post("/login", ctx -> {
                //todo: this has to be fixed
                if ("student1".equals(ctx.formParam("username")) && "password".equals(ctx.formParam("password"))) {
                    ctx.sessionAttribute("logintype", "student");
                    ctx.redirect("/lessons");
                } else if ("teacher1".equals(ctx.formParam("username")) && "password".equals(ctx.formParam("password"))) {
                    ctx.sessionAttribute("logintype", "teacher");
                    ctx.redirect("/statistics");
                } else {
                    ViewUtil.renderToCtx(ctx, "/velocity/login.vm");
                }
            });

            get("/logout", ctx -> {
                ctx.sessionAttribute("logintype", "none");
                ctx.redirect("/");
            });

            get("/", ctx -> ctx.redirect("/lessons"), roles(STUDENT));

            get("/lessons", ctx -> ViewUtil.renderToCtx(ctx, "/velocity/lessons.vm"), roles(STUDENT, TEACHER));

            get("/lessons/new", ctx -> ViewUtil.renderToCtx(ctx, "/velocity/addLesson.vm"), roles(TEACHER));

            get("/lessons/:lesson-id", ctx -> { // one specific lesson, get by id
                ViewUtil.renderToCtx(ctx, "/velocity/lesson.vm", model(
                        "lessonId", ctx.param("lesson-id")
                ));
            }, roles(STUDENT, TEACHER));

            get("/add-exercise", ctx -> ViewUtil.renderToCtx(ctx, "/velocity/addExercise.vm"), roles(TEACHER));

            get("/exercises", ctx -> ViewUtil.renderToCtx(ctx, "/velocity/lesson.vm"), roles(STUDENT, TEACHER));

            get("/about", ctx -> ViewUtil.renderToCtx(ctx, "/velocity/about.vm"), roles(STUDENT));

            get("/statistics", ctx -> ViewUtil.renderToCtx(ctx, "/velocity/statistics.vm"), roles(TEACHER));

            get("/exercises/:exercise-id", ctx -> { // one specific exercise, get by id
                String exerciseId = ctx.param("exercise-id");
                ViewUtil.renderToCtx(ctx, "/velocity/exercise.vm", model(
                        "supportedLanguages", LanguageVm.supportedLanguages,
                        "exercise", ExerciseController.getExercise(exerciseId)
                ));
            }, roles(STUDENT, TEACHER));

            path("api", () -> {

                path("lessons", () -> {
                     get(LessonController::getLessons, roles(STUDENT, TEACHER));
                     post(LessonController::createLesson, roles(TEACHER));
                     path(":lesson-id", () -> {
                         get(LessonController::getLesson, roles(STUDENT, TEACHER));
                         delete(LessonController::deleteLesson, roles(TEACHER));
                         path("exercises", () -> {
                             get(ExerciseController::getExercisesForLesson, roles(STUDENT, TEACHER));
                         });
                     });
                });

                get("/user", ctx -> {
                    if (TEACHER == UserRole.getRole(ctx)) {
                        ctx.json(new SessionInfo(true));
                    } else {
                        ctx.json(new SessionInfo(false));
                    }
                });

                post("/run-code", ctx -> { // just run the user code (Run code)
                    CodeRunningInput input = ctx.bodyAsClass(CodeRunningInput.class); // convert post-body to class
                    String result = (ScriptService.runScript(input.getLanguage(), input.getCode()));
                    ctx.json(result); // send runScript result to client, as json
                }, roles(STUDENT));

                post("/run-code-with-test", ctx -> { // run user code and test if correct (Check answer)
                    String userId = "user1";
                    CodeRunningInput input = ctx.bodyAsClass(CodeRunningInput.class); // convert json to java-object
                    Exercise exercise = ExerciseController.getExercise(input.getExerciseId()); //gets the exercise the user is solving
                    CodeRunningResult result = ScriptService.runScriptWithTest(input.getLanguage(), input.getCode(), exercise.getTestCode());

                    if (!UserController.getExerciseSolved(userId, exercise.getId())) {
                        AttemptController.registerAttempt(userId, input, result);
                    }
                    ctx.json(result); // send runScriptWithTest result to client, as json
                }, roles(STUDENT));

                path("/statistics", () -> {
                    get("/exercises", StatisticsController::getExerciseInfo, roles(TEACHER));
                    get("/students", StatisticsController::getAllUserInfo, roles(TEACHER));
                    get("/students/:student-id", StatisticsController::getStudentInfo, roles(TEACHER));
                });

            });

        });

        app.exception(NotFoundException.class, (exception, ctx) -> ctx.status(404));
        app.error(404, ctx -> ViewUtil.renderToCtx(ctx, "/velocity/notFound.vm"));

        ExerciseController.getExercises(); // connect to firebase, reduces load-time

        FakeDataUtil.writeFakeData(); //

    }

}
