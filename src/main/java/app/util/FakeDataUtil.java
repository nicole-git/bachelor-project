package app.util;

import app.model.Attempt;
import app.model.UserInfo;
import com.google.common.collect.ImmutableMap;

public class FakeDataUtil {

    public static void writeFakeData() {
        FirebaseUtil.synchronizeWrite("attempts", "{}"); // clear data
        FirebaseUtil.synchronizeWrite("attempts/fake-01", new Attempt(System.currentTimeMillis(), "fake-1", "user1", "exercise-1", "", "", 0));

        FirebaseUtil.synchronizeWrite("attempts/fake-02", new Attempt(System.currentTimeMillis(), "fake-2", "user1", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-03", new Attempt(System.currentTimeMillis(), "fake-3", "user1", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-04", new Attempt(System.currentTimeMillis(), "fake-4", "user1", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-05", new Attempt(System.currentTimeMillis(), "fake-5", "user1", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-06", new Attempt(System.currentTimeMillis(), "fake-6", "user1", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-07", new Attempt(System.currentTimeMillis(), "fake-7", "user1", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-08", new Attempt(System.currentTimeMillis(), "fake-8", "user1", "exercise-2", "", "", 0));

        FirebaseUtil.synchronizeWrite("attempts/fake-09", new Attempt(System.currentTimeMillis(), "fake-9", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-10", new Attempt(System.currentTimeMillis(), "fake-10", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-11", new Attempt(System.currentTimeMillis(), "fake-11", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-12", new Attempt(System.currentTimeMillis(), "fake-12", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-13", new Attempt(System.currentTimeMillis(), "fake-13", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-14", new Attempt(System.currentTimeMillis(), "fake-14", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-15", new Attempt(System.currentTimeMillis(), "fake-15", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-16", new Attempt(System.currentTimeMillis(), "fake-16", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-17", new Attempt(System.currentTimeMillis(), "fake-17", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-18", new Attempt(System.currentTimeMillis(), "fake-18", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-19", new Attempt(System.currentTimeMillis(), "fake-19", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-20", new Attempt(System.currentTimeMillis(), "fake-20", "user1", "exercise-3", "", "", 1));

        FirebaseUtil.synchronizeWrite("attempts/fake-21", new Attempt(System.currentTimeMillis(), "fake-21", "user2", "exercise-1", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-22", new Attempt(System.currentTimeMillis(), "fake-22", "user2", "exercise-1", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-23", new Attempt(System.currentTimeMillis(), "fake-23", "user2", "exercise-1", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-24", new Attempt(System.currentTimeMillis(), "fake-24", "user2", "exercise-1", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-25", new Attempt(System.currentTimeMillis(), "fake-25", "user2", "exercise-1", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-26", new Attempt(System.currentTimeMillis(), "fake-26", "user2", "exercise-1", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-27", new Attempt(System.currentTimeMillis(), "fake-27", "user2", "exercise-1", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-28", new Attempt(System.currentTimeMillis(), "fake-28", "user2", "exercise-1", "", "", 1));

        FirebaseUtil.synchronizeWrite("attempts/fake-29", new Attempt(System.currentTimeMillis(), "fake-29", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-30", new Attempt(System.currentTimeMillis(), "fake-30", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-31", new Attempt(System.currentTimeMillis(), "fake-31", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-32", new Attempt(System.currentTimeMillis(), "fake-32", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-33", new Attempt(System.currentTimeMillis(), "fake-33", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-34", new Attempt(System.currentTimeMillis(), "fake-34", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-35", new Attempt(System.currentTimeMillis(), "fake-35", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-36", new Attempt(System.currentTimeMillis(), "fake-36", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-37", new Attempt(System.currentTimeMillis(), "fake-37", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-38", new Attempt(System.currentTimeMillis(), "fake-38", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-39", new Attempt(System.currentTimeMillis(), "fake-39", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-40", new Attempt(System.currentTimeMillis(), "fake-40", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-41", new Attempt(System.currentTimeMillis(), "fake-41", "user2", "exercise-2", "", "", 1));
        FirebaseUtil.synchronizeWrite("attempts/fake-42", new Attempt(System.currentTimeMillis(), "fake-42", "user3", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-43", new Attempt(System.currentTimeMillis(), "fake-43", "user3", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-44", new Attempt(System.currentTimeMillis(), "fake-44", "user3", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-45", new Attempt(System.currentTimeMillis(), "fake-45", "user3", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-46", new Attempt(System.currentTimeMillis(), "fake-46", "user3", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-47", new Attempt(System.currentTimeMillis(), "fake-47", "user3", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-48", new Attempt(System.currentTimeMillis(), "fake-48", "user3", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-49", new Attempt(System.currentTimeMillis(), "fake-49", "user3", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-50", new Attempt(System.currentTimeMillis(), "fake-50", "user3", "exercise-2", "", "", 1));

    }

}
