package app.service;

import app.exception.NotFoundException;
import app.model.Attempt;
import app.model.Exercise;
import app.model.SessionInfo;
import app.model.User;
import app.model.UserInfo;
import app.security.UserRole;
import app.util.FirebaseUtil;
import com.google.firebase.database.DataSnapshot;
import io.javalin.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static app.security.UserRole.TEACHER;

public class UserService {

    // TODO: rewrite to go through each attempt instead ?
    public static List<UserInfo> getAllUserInfo() {
        List<UserInfo> userInfoList = new ArrayList<>();
        for (DataSnapshot child : FirebaseUtil.synchronizeRead("users").getChildren()) {
            User user = child.getValue(User.class);
            if (user.getUserRole() == UserRole.TEACHER) {
                continue; // skip teachers
            }
            Map<String, Boolean> exerciseSolved = new HashMap<>();
            Map<String, Integer> exerciseAttempts = new HashMap<>();
            for (Exercise exercise : ExerciseService.getExercises()) {
                exerciseSolved.put(exercise.getId(), getExerciseSolved(user.getUsername(), exercise.getId()));
                exerciseAttempts.put(exercise.getId(), getExerciseAttempts(user.getUsername(), exercise.getId()));
            }
            userInfoList.add(new UserInfo(user.getUsername(), exerciseSolved, exerciseAttempts));
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
        for (Attempt attempt : AttemptService.getAttempts()) {
            if (userId.equals(attempt.getUserId()) && exerciseId.equals(attempt.getExerciseId()) && attempt.getPercentageCorrect() == 1) {
                return true;
            }
        }
        return false;
    }

    public static int getExerciseAttempts(String userId, String exerciseId) {
        int counter = 0;
        for (Attempt attempt : AttemptService.getAttempts()) {
            if (userId.equals(attempt.getUserId()) && exerciseId.equals(attempt.getExerciseId())) {
                counter = counter + 1;
            }
        }
        return counter;
    }

    public static SessionInfo getSessionInfo(Context ctx) {
        if (TEACHER == UserRole.getRole(ctx)) {
            return new SessionInfo(ctx.sessionAttribute("username"), true);
        } else {
            return new SessionInfo(ctx.sessionAttribute("username"), false);
        }
    }

}
