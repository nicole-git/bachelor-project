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

    public static void setExerciseSolved(String userId, String exerciseId) {
        // for example: userinfo/user1/solvedExercises/exercise-1 = true
        FirebaseUtil.synchronizeWrite("userinfo/" + userId + "/solvedExercises/" + exerciseId, true);
    }

}
