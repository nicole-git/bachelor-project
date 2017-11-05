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
            new Exercise("Exercise 2", "Control Flow: if/else statements", "Create a method 'isGreaterThanTen(<i>number</i>)' that returns 'Greater than 10' if <i>number</i> is greater than 10, and 'Not greater than 10' if <i>number</i> is less than 10.", ImmutableMap.of(
                    Language.JAVASCRIPT, "isGreaterThanTen(5) === 'Not greater than 10' && isGreaterThanTen(555) === 'Greater than 10'",
                    Language.PYTHON, "isGreaterThanTen(5) == 'Not greater than 10' and isGreaterThanTen(555) == 'Greater than 10'"
            )),
            new Exercise("Exercise 3", "Control Flow: switch", "Create a method called seasonName(<i>number</i>) that takes a number from 1 to 4, and returns 'Spring', 'Summer', 'Fall', 'Winter' respectively.", ImmutableMap.of(
                    Language.JAVASCRIPT, "seasonName(1) === 'Spring' && seasonName(2) === 'Summer' && seasonName(3) === 'Fall' && seasonName(4) === 'Winter'",
                    Language.PYTHON, "seasonName(1) == 'Spring' and seasonName(2) == 'Summer' and seasonName(3) == 'Fall' and seasonName(4) == 'Winter'"
            )),
            new Exercise("Exercise 4", "For-loop", "Create a method called summation(<i>number</i>) that adds numbers 0 through number (0 + 1 + ... + number)", ImmutableMap.of(
                    Language.JAVASCRIPT, "summation(5) === 5+4+3+2+1+0 && summation(100) === 5050",
                    Language.PYTHON, "summation(5) == 5+4+3+2+1+0 and summation(100) == 5050"
            )),
            new Exercise("Exercise 5", "Filter array", "Create a method called filterEven(array) which filters out odd numbers from an array of ints", ImmutableMap.of(
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
