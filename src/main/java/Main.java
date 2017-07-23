import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().port(7000);
        app.get("/", ctx -> ctx.result("Hello World"));
    }
}
