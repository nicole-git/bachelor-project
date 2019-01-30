package app.util.fakedata;

import app.model.User;
import app.security.UserRole;
import app.util.FirebaseUtil;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.database.FirebaseDatabase;

public class FakeUsers {
    static void writeFakeData(FirebaseDatabase db) {
        FirebaseUtil.synchronizeWrite(db, "users", ImmutableMap.of(
                "student1", new User("student1", "password", UserRole.STUDENT),
                "student2", new User("student2", "password", UserRole.STUDENT),
                "student3", new User("student3", "password", UserRole.STUDENT),
                "teacher1", new User("teacher1", "password", UserRole.TEACHER)
        ));
    }
}
