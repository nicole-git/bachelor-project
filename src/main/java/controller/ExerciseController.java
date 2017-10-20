package controller;

import exception.NotFoundException;
import io.javalin.Context;
import model.Exercise;
import model.CodeRunningJob;
import util.ScriptService;

import java.util.Arrays;
import java.util.List;

public class ExerciseController {

    private static List<Exercise> exercises = Arrays.asList(
            new Exercise("Exercise 1", "Hello, World!", "Create a method called helloWorld() which prints \"Hello, World!\"", "helloWorld()", "Hello, World!"),
            new Exercise("Exercise 2", "Filter array", "Create a method called filterEven(array) which filters out odd numbers from an array of ints", "console.log(filterEven([1,2,3,4,5,6,7,8,9,10]))", "2,4,6,8,10")
    );

    public static void getAllExercises(Context ctx) {
        ctx.json(exercises);
    }

    public static Exercise getExercise(String exerciseId) throws NotFoundException {
        for (Exercise exercise : exercises) {
            if (exercise.id.equals(exerciseId)) {
                return exercise;
            }
        }
        throw new NotFoundException();
    }

    public static void runCode(Context ctx) {
        CodeRunningJob input = ctx.bodyAsClass(CodeRunningJob.class); // convert post-body to class
        ctx.json(ScriptService.runScript(input.language, input.code)); // return runScript result to client, as json
    }

    public static void runCodeWithTest(Context ctx) throws NotFoundException {
        CodeRunningJob input = ctx.bodyAsClass(CodeRunningJob.class);
        Exercise exercise = getExercise(input.exerciseId);
        ctx.json(ScriptService.runScriptWithTest(input.language, input.code, exercise.testCode, exercise.expectedValue));
    }

}
