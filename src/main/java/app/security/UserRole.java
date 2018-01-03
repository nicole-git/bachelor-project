package app.security;

import io.javalin.Context;
import io.javalin.security.Role;

public enum UserRole implements Role {

    TEACHER, STUDENT;

    public static UserRole getRole(Context ctx) {
        if ("student".equals(ctx.sessionAttribute("logintype"))) {
            return STUDENT;
        }
        if ("teacher".equals(ctx.sessionAttribute("logintype"))) {
            return TEACHER;
        }
        return null;
    }
}
