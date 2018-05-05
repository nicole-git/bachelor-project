package app.util;

import io.javalin.Context;

import java.util.Map;

import static io.javalin.translator.template.TemplateUtil.model;

public class ViewUtil {

    //put path parameter and login type in velocity model
    public static void renderHtmlFrame(Context ctx, String templatePath) {
        ctx.renderVelocity(templatePath, model(
                "logintype", ctx.sessionAttribute("logintype"),
                "lessonId", ctx.param(":lesson-id"),
                "exerciseId", ctx.param(":exercise-id")
        ));
    }

}
