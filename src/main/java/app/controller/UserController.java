package app.controller;

import app.exception.NotFoundException;
import app.model.UserInfo;
import app.util.FirebaseUtil;
import com.google.firebase.database.DataSnapshot;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    public static List<UserInfo> getAllUserInfo() {
        List<UserInfo> userInfoList = new ArrayList<>();
        for (DataSnapshot child : FirebaseUtil.synchronizeRead("userinfo").getChildren()) {
            userInfoList.add(child.getValue(UserInfo.class));
        }
        return userInfoList;
    }

    public static UserInfo getUserInfoByUserId(String userId) throws NotFoundException {
        for (UserInfo userInfo : getAllUserInfo()) {
            if (userInfo.userId.equals(userId)) {
                return userInfo;
            }
        }
        throw new NotFoundException();
    }

    public static boolean getExerciseSolved(String userId, String exerciseId) {
        return FirebaseUtil.synchronizeRead("userinfo/" + userId + "/exerciseToSolved/" + exerciseId).getValue(Boolean.class);
    }


    public static void setExerciseSolved(String userId, String exerciseId) {
        // for example: userinfo/user1/exerciseToSolved/exercise-1 = true
        FirebaseUtil.synchronizeWrite("userinfo/" + userId + "/exerciseToSolved/" + exerciseId, true);
    }

    public static int getExercisesAttempts(String userId, String exerciseId) {
        return FirebaseUtil.synchronizeRead("userinfo/" + userId + "/exerciseToAttempts/" + exerciseId).getValue(Integer.class);
    }

    public static void incrementExerciseAttempts(String userId, String exerciseId) {
        FirebaseUtil.synchronizeWrite("userinfo/" + userId + "/exerciseToAttempts/" + exerciseId, getExercisesAttempts(userId, exerciseId) + 1);
    }

}
