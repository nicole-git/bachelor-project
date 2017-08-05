import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Javalin app = Javalin.create()
            .port(7000)
            .enableStaticFiles("/public")
            .start();

        app.get("/run-code", ctx -> {
            ctx.renderVelocity("/velocity/code-editor.vm", new HashMap<>());
        });

        app.post("/run-code", ctx -> {
            String input = ctx.formParam("code");
            String output = runCode(input);
            Map<String, Object> model = new HashMap<>();
            model.put("codeInput", input);
            model.put("codeOutput", output);
            ctx.renderVelocity("/velocity/code-editor.vm", model);
        });

        app.get("/about", ctx -> {
            ctx.renderVelocity("/velocity/about.vm", new HashMap<>());
        });
    }

    public static String runCode(String input){
        return new StringBuilder(input).reverse().toString();
    }

}
