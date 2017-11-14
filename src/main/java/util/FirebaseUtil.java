package util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class FirebaseUtil {

    public static void initFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("deploy/serviceAccountKey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://nubk-oppgave9.firebaseio.com/")
                .build();
        FirebaseApp.initializeApp(options);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        initFirebase();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getValue());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        Map<String, String> usernamePasswordMap = ImmutableMap.of(
          "user1", "password1",
          "user2", "password2"
        );

        ref.setValueAsync(usernamePasswordMap);

        Thread.sleep(2000);

    }

}
