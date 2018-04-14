package app.controller;

import app.exception.NotFoundException;
import app.model.Exercise;
import app.model.UserInfo;
import app.util.FirebaseUtil;
import app.viewmodel.ExerciseVm;
import com.google.firebase.database.DataSnapshot;
import io.javalin.Context;

import java.util.ArrayList;
import java.util.List;
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

    // todo: fix this mess
    public static List<ExerciseVm> getExerciseVms() throws NotFoundException {
        List<ExerciseVm> exercises = new ArrayList<>();
        UserInfo userInfo = UserController.getUserInfoByUserId("user1");
        for (Exercise exercise : getExercises()) {
            exercises.add(new ExerciseVm(exercise, userInfo));
        }
        return exercises;
    }

    public static void getExercisesForLesson(Context ctx) {
        ctx.json(ExerciseController.getExercisesForLesson(ctx.param("lesson-id")));
    }
}
