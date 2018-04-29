package app;

import app.controller.*;
import app.exception.InvalidLoginException;
import app.exception.NotFoundException;
import app.util.FakeDataUtil;
import app.util.FirebaseUtil;
import app.util.ViewUtil;
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
                .accessManager(LoginController::accessManager)
                .start();

        app.routes(() -> {

            get("/login", ctx -> ViewUtil.renderToCtx(ctx, "/velocity/login.vm"));

            post("/login", LoginController::login);

            get("/logout", LoginController::logout);

            get("/", ctx -> ctx.redirect("/lessons"), roles(STUDENT));

            get("/lessons", ctx -> ViewUtil.renderToCtx(ctx, "/velocity/lessons.vm"), roles(STUDENT, TEACHER));

            get("/lessons/:lesson-id", ctx -> { // one specific lesson, get by id
                ViewUtil.renderToCtx(ctx, "/velocity/lesson.vm", model(
                        "lessonId", ctx.param(":lesson-id")
                ));
            }, roles(STUDENT, TEACHER));

            get("/exercises", ctx -> ViewUtil.renderToCtx(ctx, "/velocity/lesson.vm"), roles(STUDENT, TEACHER));

            get("/about", ctx -> ViewUtil.renderToCtx(ctx, "/velocity/about.vm"), roles(STUDENT));

            get("/statistics", ctx -> ViewUtil.renderToCtx(ctx, "/velocity/statistics.vm"), roles(TEACHER));

            get("/exercises/:exercise-id", ctx -> {
                ViewUtil.renderToCtx(ctx, "/velocity/exercise.vm", model(
                        "exerciseId", ctx.param(":exercise-id")
                ));
            }, roles(STUDENT, TEACHER));

            get("/lessons/:lesson-id/exercises/:exercise-id", ctx -> {
                ViewUtil.renderToCtx(ctx, "/velocity/editExercise.vm", model(
                        "lessonId", ctx.param(":lesson-id"),
                        "exerciseId", ctx.param(":exercise-id")
                ));
            }, roles(TEACHER));

            path("api", () -> {
                get("/user", UserController::getSessionInfo);
                path("/lessons", () -> {
                    get(LessonController::getLessons, roles(STUDENT, TEACHER));
                    post(LessonController::createLesson, roles(TEACHER));
                    path("/:lesson-id", () -> {
                        get(LessonController::getLesson, roles(STUDENT, TEACHER));
                        patch(LessonController::updateLesson, roles(TEACHER));
                        delete(LessonController::deleteLesson, roles(TEACHER));
                        path("/exercises", () -> {
                            get(ExerciseController::getExercisesForLesson, roles(STUDENT, TEACHER));
                            post(ExerciseController::createExerciseForLesson, roles(TEACHER));
                            path("/:exercise-id", () -> {
                                delete(ExerciseController::deleteExerciseForLesson, roles(TEACHER));
                            });
                        });
                    });
                });
                path("/exercises", () -> {
                    path("/:exercise-id", () -> {
                        get(ExerciseController::getExercise, roles(STUDENT, TEACHER));
                        patch(ExerciseController::updateExercise, roles(TEACHER));
                        patch("/add-language", ExerciseController::addLanguageToExercise, roles(TEACHER));
                        delete("/code", ExerciseController::deleteLanguage, roles(TEACHER));
                    });
                });
                post("/run-code", CodeRunningController::runCode, roles(STUDENT, TEACHER));
                post("/run-code-with-test", CodeRunningController::runCodeWithTest, roles(STUDENT, TEACHER));
                path("/statistics", () -> {
                    get("/exercises", StatisticsController::getExerciseInfo, roles(TEACHER));
                    get("/students", StatisticsController::getAllUserInfo, roles(TEACHER));
                    get("/students/:student-id", StatisticsController::getStudentInfo, roles(TEACHER));
                });
            });
        });

        app.exception(InvalidLoginException.class, (exception, ctx) -> ViewUtil.renderToCtx(ctx, "/velocity/login.vm"));
        app.exception(NotFoundException.class, (exception, ctx) -> ctx.status(404));
        app.error(404, ctx -> ViewUtil.renderToCtx(ctx, "/velocity/notFound.vm"));

        FakeDataUtil.writeFakeData();

    }

}
