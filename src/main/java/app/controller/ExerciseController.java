package app.controller;

import app.exception.NotFoundException;
import app.model.Exercise;
import app.model.UserInfo;
import app.util.FirebaseUtil;
import app.viewmodel.ExerciseVm;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ExerciseController {

    public static List<Exercise> getAllExercises() {
        return getExercises();
    }

    public static Exercise getExercise(String exerciseId) throws NotFoundException {
        for (Exercise exercise : getExercises()) {
            if (exercise.getId().equals(exerciseId)) {
                return exercise;
            }
        }
        throw new NotFoundException();
    }

    private static List<Exercise> getExercises() {
        List<Exercise> exercises = new ArrayList<>();
        for (DataSnapshot child : FirebaseUtil.synchronizeRead("exercises").getChildren()) {
            exercises.add(child.getValue(Exercise.class));
        }
        return exercises;
    }

    public static List<ExerciseVm> getExerciseVms() throws NotFoundException {
        List<ExerciseVm> exercises = new ArrayList<>();
        UserInfo userInfo = UserController.getUserInfoByUserId("user1");
        for (Exercise exercise : getAllExercises()) {
            exercises.add(new ExerciseVm(exercise, userInfo));
        }
        return exercises;
    }

}
