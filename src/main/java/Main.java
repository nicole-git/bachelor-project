import io.javalin.Javalin;

import static io.javalin.translator.template.TemplateUtil.model;

public class Main {

    public static void main(String[] args) {

        Javalin app = Javalin.create()
                .port(7000)
                .enableStaticFiles("/public")
                .start();

        app.get("/run-code", ctx -> {
            ctx.renderVelocity("/velocity/code-editor.vm");
        });

        app.post("/run-code", ctx -> {
            String input = ctx.formParam("code");
            String output = runCode(input);
            ctx.renderVelocity("/velocity/code-editor.vm", model(
                    "codeInput", input,
                    "codeOutput", output
            ));
        });

        app.get("/about", ctx -> {
            ctx.renderVelocity("/velocity/about.vm");
        });
    }

    public static String runCode(String input) {
        return new StringBuilder(input).reverse().toString();
    }

}
