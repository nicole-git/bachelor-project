import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create();
        app.port(7000);
        app.enableStaticFiles("/public");
        app.start();
        app.post("/run-code", ctx -> ctx.result(ctx.formParam("code")));
    }
}
