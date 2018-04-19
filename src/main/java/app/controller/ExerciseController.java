package app.controller;

import app.exception.NotFoundException;
import app.model.Exercise;
import app.model.Lesson;
import app.util.FirebaseUtil;
import com.google.firebase.database.DataSnapshot;
import io.javalin.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExerciseController {

    public static Exercise getExercise(String exerciseId) throws NotFoundException {
        Exercise exercise = FirebaseUtil.synchronizeRead("exercises/" + exerciseId).getValue(Exercise.class);
        if (exercise == null) {
            throw new NotFoundException();
        }
        return exercise;
    }

    public static List<Exercise> getExercises() {
        List<Exercise> exercises = new ArrayList<>();
        for (DataSnapshot child : FirebaseUtil.synchronizeRead("exercises").getChildren()) {
            exercises.add(child.getValue(Exercise.class));
        }
        return exercises;
    }

    public static List<Exercise> getExercisesForLesson(String lessonId) {
        List<String> exerciseIds = LessonController.getLesson(lessonId).getExerciseIds();
        return exerciseIds.stream().map(id -> getExercise(id)).collect(Collectors.toList());
    }

    public static void getExercisesForLesson(Context ctx) {
        ctx.json(ExerciseController.getExercisesForLesson(ctx.param(":lesson-id")));
    }

    public static void getExercise(Context ctx) {
        ctx.json(ExerciseController.getExercise(ctx.param(":exercise-id")));
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
        Lesson lesson = LessonController.getLesson(ctx.param(":lesson-id"));
        lesson.getExerciseIds().add(newExercise.getId());
        FirebaseUtil.synchronizeWrite("lessons/" + lesson.getId(), lesson);
        FirebaseUtil.synchronizeWrite("exercises/" + newExercise.getId(), newExercise);
        ctx.json(newExercise);
    }

    public static void deleteExerciseForLesson(Context ctx) {
        FirebaseUtil.synchronizeWrite("exercises/" + ctx.param(":exercise-id"), null);
        Lesson lesson = LessonController.getLesson(ctx.param(":lesson-id"));
        lesson.getExerciseIds().remove(ctx.param(":exercise-id"));
        FirebaseUtil.synchronizeWrite("lessons/" + ctx.param(":lesson-id"), lesson);
    }
}
