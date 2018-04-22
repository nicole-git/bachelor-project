package app.controller;

import app.model.Exercise;
import app.model.Lesson;
import app.service.ExerciseService;
import app.service.LessonService;
import io.javalin.Context;

import java.util.UUID;

public class ExerciseController {

    public static void getExercisesForLesson(Context ctx) {
        ctx.json(ExerciseService.getExercisesForLesson(ctx.param(":lesson-id")));
    }

    public static void getExercise(Context ctx) {
        ctx.json(ExerciseService.getExercise(ctx.param(":exercise-id")));
    }

    public static void createExerciseForLesson(Context ctx) {
        Exercise exerciseInput = ctx.bodyAsClass(Exercise.class);
        Exercise newExercise = new Exercise(
                UUID.randomUUID().toString(),
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
}
