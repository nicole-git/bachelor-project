import io.javalin.Javalin;
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
            String language = ctx.formParam("language");
            String input = ctx.formParam("code");
            String output = ScriptService.runScript(language, input);
            ctx.renderVelocity("/velocity/code-editor.vm", model(
                    "supportedLanguages", supportedLanguages,
                    "selectedLanguage", language,
                    "codeInput", input,
                    "codeOutput", output
            ));
        });

        app.get("/about", ctx -> {
            ctx.renderVelocity("/velocity/about.vm");
        });
    }

}
