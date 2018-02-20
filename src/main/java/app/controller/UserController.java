package app.controller;

import app.exception.NotFoundException;
import app.model.Attempt;
import app.model.Exercise;
import app.model.UserInfo;
import app.util.FirebaseUtil;
import com.google.firebase.database.DataSnapshot;

import java.util.*;

public class UserController {

    // TODO: this is very inefficient. should  rewrite to go through each attempt instead
    public static List<UserInfo> getAllUserInfo() {
        List<UserInfo> userInfoList = new ArrayList<>();
        for (DataSnapshot child : FirebaseUtil.synchronizeRead("users").getChildren()) {
            String userName = child.getKey();
            Map<String, Boolean> exerciseSolved = new HashMap<>();
            Map<String, Integer> exerciseAttempts = new HashMap<>();
            for (Exercise exercise : ExerciseController.getAllExercises()) {
                exerciseSolved.put(exercise.getId(), getExerciseSolved(userName, exercise.getId()));
                exerciseAttempts.put(exercise.getId(), getExerciseAttempts(userName, exercise.getId()));
            }
            userInfoList.add(new UserInfo(userName, exerciseSolved, exerciseAttempts));
        }
        return userInfoList;
    }

    public static UserInfo getUserInfoByUserId(String userId) throws NotFoundException {
        for (UserInfo userInfo : getAllUserInfo()) {
            if (userInfo.getUserId().equals(userId)) {
                return userInfo;
            }
        }
        throw new NotFoundException();
    }

    public static boolean getExerciseSolved(String userId, String exerciseId) {
        for (DataSnapshot child : FirebaseUtil.synchronizeRead("attempts").getChildren()) {
            Attempt attempt = child.getValue(Attempt.class);
            if (userId.equals(attempt.getUserId()) && exerciseId.equals(attempt.getExerciseId()) && attempt.getPercentageCorrect() == 1) {
                return true;
            }
        }
        return false;
    }

    public static int getExerciseAttempts(String userId, String exerciseId) {
        int counter = 0;
        for (DataSnapshot child : FirebaseUtil.synchronizeRead("attempts").getChildren()) {
            Attempt attempt = child.getValue(Attempt.class);
            if (userId.equals(attempt.getUserId()) && exerciseId.equals(attempt.getExerciseId())) {
                counter = counter + 1;
            }
        }
        return counter;
    }

}
