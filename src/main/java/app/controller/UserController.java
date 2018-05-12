package app.controller;

import app.service.UserService;
import io.javalin.Context;

public class UserController {

    public static void getSessionInfo(Context ctx) {
        ctx.json(UserService.getSessionInfo(ctx));
    }

}
