package app.controller;

import app.model.Exercise;
import app.model.Lesson;
import app.model.SessionInfo;
import app.model.StudentExercise;
import app.service.ExerciseService;
import app.service.LessonService;
import app.service.UserService;
import app.util.FirebaseUtil;
import io.javalin.Context;
import java.util.List;
import java.util.stream.Collectors;

public class ExerciseController {

    public static void getExercisesForLesson(Context ctx) {
        SessionInfo sessionInfo = UserService.getSessionInfo(ctx);
        List<Exercise> exercises = ExerciseService.getExercisesForLesson(ctx.param(":lesson-id"));
        if (sessionInfo.isAdmin()) {
            ctx.json(exercises);
        } else { // students don't get to view the test-code, and they have a "solved" boolean
            ctx.json(
                exercises.stream()
                    .map(e -> new StudentExercise(e, sessionInfo.getUsername()))
                    .collect(Collectors.toList())
            );
        }
    }

    public static void getExercise(Context ctx) {
        SessionInfo sessionInfo = UserService.getSessionInfo(ctx);
        Exercise exercise = ExerciseService.getExercise(ctx.param(":exercise-id"));
        if (sessionInfo.isAdmin()) {
            ctx.json(exercise);
        } else { // students don't get to view the test-code, and they have a "solved" boolean
            ctx.json(new StudentExercise(exercise, sessionInfo.getUsername()));
        }
    }

    public static void createExerciseForLesson(Context ctx) {
        Exercise exerciseInput = ctx.bodyAsClass(Exercise.class);
        Exercise newExercise = new Exercise(
                FirebaseUtil.randomId(),
                exerciseInput.getTitle(),
                exerciseInput.getDescription(),
                exerciseInput.getInstructions(),
                exerciseInput.getStartCode(),
                exerciseInput.getTestCode()
        );
        Lesson lesson = LessonService.getLesson(ctx.param(":lesson-id"));
        lesson.getExerciseIds().add(newExercise.getId());
        LessonService.saveLesson(lesson);
        ExerciseService.saveExercise(newExercise);
        ctx.json(newExercise);
    }

    public static void deleteExerciseForLesson(Context ctx) {
        ExerciseService.deleteExercise(ctx.param(":exercise-id"));
        Lesson lesson = LessonService.getLesson(ctx.param(":lesson-id"));
        lesson.getExerciseIds().remove(ctx.param(":exercise-id"));
        LessonService.saveLesson(lesson);
    }

    public static void updateExercise(Context ctx) {
        Exercise userInput = ctx.bodyAsClass(Exercise.class);
        Exercise exerciseToBeUpdated = ExerciseService.getExercise(ctx.param(":exercise-id"));
        exerciseToBeUpdated.setTitle(userInput.getTitle());
        exerciseToBeUpdated.setDescription(userInput.getDescription());
        exerciseToBeUpdated.setInstructions(userInput.getInstructions());
        ExerciseService.saveExercise(exerciseToBeUpdated);
    }

    public static void addLanguageToExercise(Context ctx) {
        Exercise userInput = ctx.bodyAsClass(Exercise.class);
        Exercise exerciseToBeUpdated = ExerciseService.getExercise(ctx.param(":exercise-id"));
        exerciseToBeUpdated.getStartCode().putAll(userInput.getStartCode());
        exerciseToBeUpdated.getTestCode().putAll(userInput.getTestCode());
        ExerciseService.saveExercise(exerciseToBeUpdated);
    }

    public static void deleteLanguage(Context ctx) {
        Exercise exerciseToBeUpdated = ExerciseService.getExercise(ctx.param(":exercise-id"));
        exerciseToBeUpdated.getStartCode().remove(ctx.queryParam("language"));
        exerciseToBeUpdated.getTestCode().remove(ctx.queryParam("language"));
        ExerciseService.saveExercise(exerciseToBeUpdated);
    }
}
