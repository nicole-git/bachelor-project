package app.util;

import app.model.UserInfo;
import com.google.common.collect.ImmutableMap;

public class FakeDataUtil {

    public static void writeFakeData() {
        FirebaseUtil.synchronizeWrite("userinfo/user1", new UserInfo("user1",
            ImmutableMap.of(
                "exercise-1", false,
                "exercise-2", true,
                "exercise-3", true,
                "exercise-4", false,
                "exercise-5", false
            ),
            ImmutableMap.of(
                "exercise-1", 1,
                "exercise-2", 7,
                "exercise-3", 12,
                "exercise-4", 0,
                "exercise-5", 0
            )));
        FirebaseUtil.synchronizeWrite("userinfo/user2", new UserInfo("user2",
            ImmutableMap.of(
                "exercise-1", true,
                "exercise-2", true,
                "exercise-3", false,
                "exercise-4", false,
                "exercise-5", false
            ),
            ImmutableMap.of(
                "exercise-1", 8,
                "exercise-2", 22,
                "exercise-3", 0,
                "exercise-4", 0,
                "exercise-5", 0
            )));
        FirebaseUtil.synchronizeWrite("userinfo/user3", new UserInfo("user3",
            ImmutableMap.of(
                "exercise-1", false,
                "exercise-2", false,
                "exercise-3", false,
                "exercise-4", false,
                "exercise-5", false
            ),
            ImmutableMap.of(
                "exercise-1", 0,
                "exercise-2", 0,
                "exercise-3", 0,
                "exercise-4", 0,
                "exercise-5", 0
            )));
    }

}
