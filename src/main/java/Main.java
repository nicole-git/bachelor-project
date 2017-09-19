import io.javalin.Javalin;
import util.ScriptService;

import static io.javalin.translator.template.TemplateUtil.model;

public class Main {

    public static void main(String[] args) {

        Javalin app = Javalin.create()
                .port(7000)
                .enableStaticFiles("/public")
                .start();

        app.get("/", ctx -> ctx.redirect("/run-code"));

        app.get("/run-code", ctx -> {
            ctx.renderVelocity("/velocity/code-editor.vm");
        });

        app.post("/run-code", ctx -> {
            String language = ctx.formParam("language");
            String input = ctx.formParam("code");
            String output = ScriptService.runScript(language, input);
            ctx.renderVelocity("/velocity/code-editor.vm", model(
                    "codeInput", input,
                    "codeOutput", output
            ));
        });

        app.get("/about", ctx -> {
            ctx.renderVelocity("/velocity/about.vm");
        });
    }

}
