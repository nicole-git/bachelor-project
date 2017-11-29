package app.security;

import io.javalin.Context;
import io.javalin.security.Role;

public enum MyRole implements Role {
    ANYONE, LOGGED_IN;

    public static MyRole getRole(Context ctx) {
        if (ctx.sessionAttribute("logged_in") != null && (boolean) ctx.sessionAttribute("logged_in")) {
            return LOGGED_IN;
        }
        return null;
    }
}