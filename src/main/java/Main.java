import io.javalin.Javalin;
import model.LanguageAndCode;
import model.LanguageViewModel;
import util.ScriptService;

import java.util.Arrays;
import java.util.List;

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

        app.get("/", ctx -> ctx.redirect("/run-code"));

        app.get("/run-code", ctx -> {
            ctx.renderVelocity("/velocity/code-editor.vm", model("supportedLanguages", supportedLanguages));
        });

        app.post("/run-code", ctx -> {
            LanguageAndCode input = ctx.bodyAsClass(LanguageAndCode.class); // convert post-body to class
            ctx.json(ScriptService.runScript(input.language, input.code)); // return runScript result to client, as json
        });

        app.get("/about", ctx -> {
            ctx.renderVelocity("/velocity/about.vm");
        });
    }

}
