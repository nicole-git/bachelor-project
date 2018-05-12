package app.controller;

import app.exception.InvalidLoginException;
import app.model.User;
import app.security.UserRole;
import app.service.LoginService;
import app.util.ViewUtil;
import io.javalin.Context;
import io.javalin.Handler;
import io.javalin.security.Role;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class LoginController {
    public static void login(Context ctx) {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new InvalidLoginException();
        }

        User user = LoginService.getUserByUsername(username);
        if (!user.getPassword().equals(password)) {
            throw new InvalidLoginException();
        }

        if (user.getUserRole() == UserRole.STUDENT) {
            ctx.sessionAttribute("logintype", "student");
            ctx.sessionAttribute("username", user.getUsername());
            ctx.redirect("/lessons");
        } else if (user.getUserRole() == UserRole.TEACHER) {
            ctx.sessionAttribute("logintype", "teacher");
            ctx.sessionAttribute("username", user.getUsername());
            ctx.redirect("/statistics");
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("logintype", null);
        ctx.sessionAttribute("username", null);
        ctx.redirect("/");
    }

    public static void accessManager(Handler handler, Context ctx, List<Role> permittedRoles) throws Exception {
        UserRole userRole = UserRole.getRole(ctx);
        if (permittedRoles.contains(userRole)) {
            handler.handle(ctx);
        } else {
            ctx.status(401);
            ViewUtil.renderHtmlFrame(ctx, "/velocity/login.vm");
        }
    }
}
