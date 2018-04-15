package app.controller;

import app.security.UserRole;
import app.util.ViewUtil;
import io.javalin.Context;
import io.javalin.Handler;
import io.javalin.security.Role;

import java.util.List;

public class LoginController {
    public static void login(Context ctx) {
        //todo: this has to be fixed
        if ("student1".equals(ctx.formParam("username")) && "password".equals(ctx.formParam("password"))) {
            ctx.sessionAttribute("logintype", "student");
            ctx.redirect("/lessons");
        } else if ("teacher1".equals(ctx.formParam("username")) && "password".equals(ctx.formParam("password"))) {
            ctx.sessionAttribute("logintype", "teacher");
            ctx.redirect("/statistics");
        } else {
            ViewUtil.renderToCtx(ctx, "/velocity/login.vm");
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("logintype", "none");
        ctx.redirect("/");
    }

    public static void accessManager(Handler handler, Context ctx, List<Role> permittedRoles) throws Exception {
        UserRole userRole = UserRole.getRole(ctx);
        if (permittedRoles.contains(userRole)) {
            handler.handle(ctx);
        } else {
            ctx.status(401);
            ViewUtil.renderToCtx(ctx, "/velocity/login.vm");
        }
    }
}
