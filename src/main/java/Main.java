import controller.ExerciseController;
import exception.NotFoundException;
import io.javalin.Javalin;
import model.CodeRunningJob;
import model.Exercise;
import model.LanguageViewModel;
import util.ScriptService;

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

            get("/exercises/:exercise-id", ctx -> { // one specific exercise, get by id
                String exerciseId = ctx.param("exercise-id");
                ctx.renderVelocity("/velocity/exercise.vm", model(
                        "supportedLanguages", supportedLanguages,
                        "exercise", ExerciseController.getExercise(exerciseId)
                ));
            });

            path("/api", () -> {

                get("/exercises", ctx -> {
                    ctx.json(ExerciseController.getAllExercises());
                });

                post("/run-code", ctx -> { // just run the user code (Run code)
                    CodeRunningJob input = ctx.bodyAsClass(CodeRunningJob.class); // convert post-body to class
                    ctx.json(ScriptService.runScript(input.language, input.code)); // return runScript result to client, as json
                });

                post("/run-code-with-test", ctx -> { // run user code and test if correc (Check answer)
                    CodeRunningJob input = ctx.bodyAsClass(CodeRunningJob.class);
                    Exercise exercise = ExerciseController.getExercise(input.exerciseId);
                    ctx.json(ScriptService.runScriptWithTest(input.language, input.code, exercise.testCode, exercise.expectedValue));
                });

            });

        });

        app.exception(NotFoundException.class, (exception, ctx) -> ctx.status(404));
        app.error(404, ctx -> ctx.renderVelocity("/velocity/notFound.vm"));

    }

}
