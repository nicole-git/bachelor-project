package app.util;

import app.model.Attempt;
import app.model.UserInfo;
import com.google.common.collect.ImmutableMap;

public class FakeDataUtil {

    public static void writeFakeData() {
        FirebaseUtil.synchronizeWrite("attempts", "{}"); // clear data
        FirebaseUtil.synchronizeWrite("attempts/fake-01", new Attempt("fake-1", "user1", "exercise-1", "", "", 0));

        FirebaseUtil.synchronizeWrite("attempts/fake-02", new Attempt("fake-2", "user1", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-03", new Attempt("fake-3", "user1", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-04", new Attempt("fake-4", "user1", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-05", new Attempt("fake-5", "user1", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-06", new Attempt("fake-6", "user1", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-07", new Attempt("fake-7", "user1", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-08", new Attempt("fake-8", "user1", "exercise-2", "", "", 100));

        FirebaseUtil.synchronizeWrite("attempts/fake-09", new Attempt("fake-9", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-10", new Attempt("fake-10", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-11", new Attempt("fake-11", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-12", new Attempt("fake-12", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-13", new Attempt("fake-13", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-14", new Attempt("fake-14", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-15", new Attempt("fake-15", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-16", new Attempt("fake-16", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-17", new Attempt("fake-17", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-18", new Attempt("fake-18", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-19", new Attempt("fake-19", "user1", "exercise-3", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-20", new Attempt("fake-20", "user1", "exercise-3", "", "", 100));

        FirebaseUtil.synchronizeWrite("attempts/fake-21", new Attempt("fake-21", "user2", "exercise-1", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-22", new Attempt("fake-22", "user2", "exercise-1", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-23", new Attempt("fake-23", "user2", "exercise-1", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-24", new Attempt("fake-24", "user2", "exercise-1", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-25", new Attempt("fake-25", "user2", "exercise-1", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-26", new Attempt("fake-26", "user2", "exercise-1", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-27", new Attempt("fake-27", "user2", "exercise-1", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-28", new Attempt("fake-28", "user2", "exercise-1", "", "", 100));

        FirebaseUtil.synchronizeWrite("attempts/fake-29", new Attempt("fake-29", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-30", new Attempt("fake-30", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-31", new Attempt("fake-31", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-32", new Attempt("fake-32", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-33", new Attempt("fake-33", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-34", new Attempt("fake-34", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-35", new Attempt("fake-35", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-36", new Attempt("fake-36", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-37", new Attempt("fake-37", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-38", new Attempt("fake-38", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-39", new Attempt("fake-39", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-40", new Attempt("fake-40", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-41", new Attempt("fake-41", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-42", new Attempt("fake-42", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-43", new Attempt("fake-43", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-44", new Attempt("fake-44", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-45", new Attempt("fake-45", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-46", new Attempt("fake-46", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-47", new Attempt("fake-47", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-48", new Attempt("fake-48", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-49", new Attempt("fake-49", "user2", "exercise-2", "", "", 0));
        FirebaseUtil.synchronizeWrite("attempts/fake-50", new Attempt("fake-50", "user2", "exercise-2", "", "", 100));

    }

}
