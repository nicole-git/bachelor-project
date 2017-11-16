package app.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

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

}
