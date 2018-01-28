package app.util;

import app.model.Exercise;
import app.model.Language;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class ExerciseUploadUtil {

    public static void main(String[] args) throws Exception {
        FirebaseDatabase firebaseDatabase = FirebaseUtil.initFirebase();
        DatabaseReference exercisesReference = firebaseDatabase.getReference("exercises");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        exercisesReference.setValue(getExercisesFromFileSystem(), (databaseError, databaseReference) -> {
            System.out.println("Uploaded complete.");
            countDownLatch.countDown();
        });
        System.out.println("Uploading files to firebase ... ");
        countDownLatch.await(); // will wait until count is 0
    }

    // create a map with exercise-id as key and exercise as value
    private static Map<String, Exercise> getExercisesFromFileSystem() throws IOException {
        File codeDirectory = new File("code");
        Map<String, Exercise> exerciseList = new HashMap<>();
        for (File exerciseDir : codeDirectory.listFiles()) {
            JsonNode meta = new ObjectMapper().readTree(readFile(exerciseDir, "meta.json"));
            String instructions = readFile(exerciseDir, "instructions.html");
            String jsStartCode = readFile(exerciseDir, "javascript.js");
            String pythonStartCode = readFile(exerciseDir, "python.py");
            String jsTestCode = readFile(exerciseDir, "javascript-test.js");
            String pythonTestCode = readFile(exerciseDir, "python-test.py");
            Exercise exercise = new Exercise(
                    meta.get("title").textValue().toLowerCase().replaceAll(" ", "-"),
                    meta.get("title").textValue(),
                    meta.get("description").textValue(),
                    instructions,
                    ImmutableMap.of(
                            Language.JAVASCRIPT, jsStartCode,
                            Language.PYTHON, pythonStartCode
                    ),
                    ImmutableMap.of(
                            Language.JAVASCRIPT, jsTestCode,
                            Language.PYTHON, pythonTestCode
                    )
            );
            exerciseList.put(exercise.getId(), exercise);
        }
        return exerciseList;
    }

    private static String readFile(File exerciseFile, String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(exerciseFile.getAbsolutePath() + "/" + path)));
    }

}
