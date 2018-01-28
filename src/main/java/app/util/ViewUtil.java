package app.util;

import io.javalin.Context;

import java.util.Map;

import static io.javalin.translator.template.TemplateUtil.model;

public class ViewUtil {

    public static void renderToCtx(Context ctx, String path) {
        ctx.renderVelocity(path, getModel(ctx));
    }

    public static void renderToCtx(Context ctx, String path, Map<String, Object> model) {
        Map<String, Object> baseModel = getModel(ctx);
        baseModel.putAll(model);
        ctx.renderVelocity(path, baseModel);
    }

    private static Map<String, Object> getModel(Context ctx) {
        return model("logintype", ctx.sessionAttribute("logintype"));
    }

}
