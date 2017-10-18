import controller.ExerciseController;
import exception.PageNotFoundException;
import io.javalin.Javalin;
import model.LanguageViewModel;

import java.util.Arrays;
import java.util.List;

import static io.javalin.ApiBuilder.get;
import static io.javalin.ApiBuilder.path;
import static io.javalin.ApiBuilder.post;
import static io.javalin.translator.template.TemplateUtil.model;

public class Main {

    private static List<LanguageViewModel> supportedLanguages = Arrays.asList(
            new LanguageViewModel("javascript", "JavaScript"),
            new LanguageViewModel("python", "Python"),
            new LanguageViewModel("ruby", "Ruby"),
            new LanguageViewModel("groovy", "Groovy")
    );

    public static void main(String[] args) {

        Javalin app = Javalin.create()
                .port(7000)
                .enableStaticFiles("/public")
                .start();

        app.routes(() -> {
            get("/", ctx -> ctx.redirect("/exercises"));

            get("/about", ctx -> ctx.renderVelocity("/velocity/about.vm"));

            get("/exercises", ctx -> ctx.renderVelocity("/velocity/exercises.vm"));

            get("/exercises/:exercise-id", ctx -> {
                String exerciseId = ctx.param("exercise-id");
                ctx.renderVelocity("/velocity/exercise.vm", model(
                        "supportedLanguages", supportedLanguages,
                        "exercise", ExerciseController.getExercise(exerciseId)
                ));
            });
        });

        app.exception(PageNotFoundException.class, (exception, ctx) -> ctx.status(404));
        app.error(404, ctx -> ctx.renderVelocity("/velocity/notFound.vm"));

        app.routes(() -> {
            path("/api", () -> {
                get("/exercises", ExerciseController::getAllExercises);
                post("/run-code", ExerciseController::runCode);
            });
        });
    }

}
