package app.util.fakedata;

import app.util.FirebaseUtil;
import com.google.firebase.database.FirebaseDatabase;

public class WriteFakeData {
    public static void main(String[] args) throws Exception {
        FirebaseDatabase db = FirebaseUtil.initFirebase();
        System.out.println("Writing fake data to firebase ... ");
        System.out.println("Writing lessons ...");
        FakeLessons.writeFakeData(db);
        System.out.println("Writing users ...");
        FakeUsers.writeFakeData(db);
        System.out.println("Writing attempts ...");
        FakeAttempts.writeFakeData(db);
        System.out.println("Done!");
    }
}
