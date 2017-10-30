import controller.ExerciseController;
import exception.NotFoundException;
import io.javalin.Javalin;
import model.CodeRunningJob;
import model.Exercise;
import model.Language;
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
            new LanguageViewModel(Language.JAVASCRIPT, "JavaScript"),
            new LanguageViewModel(Language.PYTHON, "Python"),
            new LanguageViewModel(Language.RUBY, "Ruby"),
            new LanguageViewModel(Language.JAVASCRIPT, "Groovy")
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
