package app.controller;

import app.model.CodeRunningInput;
import app.model.CodeRunningResult;
import app.model.Exercise;
import app.service.AttemptService;
import app.service.ExerciseService;
import app.service.UserService;
import app.util.ScriptService;
import io.javalin.Context;

public class CodeRunningController {

    public static void runCode(Context ctx) {
        CodeRunningInput input = ctx.bodyAsClass(CodeRunningInput.class); // convert post-body to class
        CodeRunningResult result = ScriptService.runScript(input.getLanguage(), input.getCode());
        ctx.json(result); // send runScript result to client, as json
    }

    public static void runCodeWithTest(Context ctx) {
        String username = UserService.getSessionInfo(ctx).getUsername();
        CodeRunningInput input = ctx.bodyAsClass(CodeRunningInput.class); // convert json to java-object
        Exercise exercise = ExerciseService.getExercise(input.getExerciseId()); //gets the exercise the user is solving
        CodeRunningResult result = ScriptService.runScriptWithTest(input.getLanguage(), input.getCode(), exercise.getTestCode());
        if (!UserService.getExerciseSolved(username, exercise.getId())) {
            AttemptService.registerAttempt(username, input, result);
        }
        ctx.json(result); // send runScriptWithTest result to client, as json
    }

}
