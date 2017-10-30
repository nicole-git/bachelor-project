package controller;

import exception.NotFoundException;
import model.Exercise;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import model.Language;

public class ExerciseController {

    private static List<Exercise> exercises = Arrays.asList(
            new Exercise("Exercise 1", "Hello, World!", "Create a method called helloWorld() which returns \"Hello, World!\"", ImmutableMap.of(
                    Language.JAVASCRIPT, "helloWorld() === 'Hello, World!'",
                    Language.PYTHON, "helloWorld() == 'Hello, World!'"
            )),
            new Exercise("Exercise 2", "Filter array", "Create a method called filterEven(array) which filters out odd numbers from an array of ints", ImmutableMap.of(
                    Language.JAVASCRIPT, "filterEven([1,2,3,4,5,6,7,8,9,10]).toString() === [2,4,6,8,10].toString()",
                    Language.PYTHON, "filterEven([1,2,3,4,5,6,7,8,9,10]) == [2, 4, 6, 8, 10]"
            ))
    );

    public static List<Exercise> getAllExercises() {
        return exercises;
    }

    public static Exercise getExercise(String exerciseId) throws NotFoundException {
        for (Exercise exercise : exercises) {
            if (exercise.id.equals(exerciseId)) {
                return exercise;
            }
        }
        throw new NotFoundException();
    }

}
