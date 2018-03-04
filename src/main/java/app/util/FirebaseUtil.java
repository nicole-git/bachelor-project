package app.util;

import app.Main;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.util.concurrent.CountDownLatch;

public class FirebaseUtil {

    // this method creates a connection to firebase and returns a FirebaseDatabase-object
    // that we can use to read from and write to the database
    public static FirebaseDatabase initFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream("deploy/serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://nubk-oppgave9.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);
            return FirebaseDatabase.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSnapshot synchronizeRead(String reference) {
        DatabaseReference ref = Main.firebaseDatabase.getReference(reference);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        final DataSnapshot[] snapshot = new DataSnapshot[1];
        ref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                snapshot[0] = dataSnapshot;
                countDownLatch.countDown();
            }

            public void onCancelled(DatabaseError databaseError) {
            }
        });
        try {
            countDownLatch.await();
            return snapshot[0];
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void synchronizeWrite(String reference, Object object) {
        synchronizeWrite(Main.firebaseDatabase, reference, object);
    }

    public static void synchronizeWrite(FirebaseDatabase db, String reference, Object object) {
        DatabaseReference ref = db.getReference(reference);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ref.setValue(object, (databaseError, databaseReference) -> countDownLatch.countDown());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
