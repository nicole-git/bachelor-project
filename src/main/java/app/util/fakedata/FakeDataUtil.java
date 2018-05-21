package app.util.fakedata;

import app.util.FirebaseUtil;
import com.google.firebase.database.FirebaseDatabase;

public class FakeDataUtil {
    public static void main(String[] args) throws Exception {
        FirebaseDatabase db = FirebaseUtil.initFirebase();
        System.out.println("Writing fake data to firebase ... ");
        System.out.println("Writing lessons ...");
        FakeLessonUtil.writeFakeData(db);
        System.out.println("Writing users ...");
        FakeUsersUtil.writeFakeData(db);
        System.out.println("Writing attempts ...");
        FakeAttemptsUtil.writeFakeData(db);
        System.out.println("Done!");
    }
}
