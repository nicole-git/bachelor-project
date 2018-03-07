package app.util;

import app.model.Exercise;
import app.model.Language;
import app.model.Lesson;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class LessonUploadUtil {

    public static void main(String[] args) throws Exception {
        FirebaseDatabase db = FirebaseUtil.initFirebase();
        Lesson lesson1 = new ObjectMapper().readValue(readFileAsString("lesson/lesson1.json"), Lesson.class);
        lesson1.setText(readFileAsString("lesson/lesson1.html"));
        FirebaseUtil.synchronizeWrite(db, "lessons", ImmutableMap.of(lesson1.getId(), lesson1));
        System.out.println("Uploading files to firebase ... ");
        FirebaseUtil.synchronizeWrite(db, "exercises", getExercisesFromFileSystem());
        System.out.println("Uploading complete!");
    }

    // create a map with exercise-id as key and exercise as value
    private static Map<String, Exercise> getExercisesFromFileSystem() throws IOException {
        File codeDirectory = new File("code");
        Map<String, Exercise> exerciseList = new HashMap<>();
        for (File exerciseDir : codeDirectory.listFiles()) {
            JsonNode metaInfo = new ObjectMapper().readTree(readFileAsString(exerciseDir, "meta.json"));
            String instructions = readFileAsString(exerciseDir, "instructions.html");
            String jsStartCode = readFileAsString(exerciseDir, "javascript.js");
            String pythonStartCode = readFileAsString(exerciseDir, "python.py");
            List<String> jsTestCode = new ArrayList<>();
            File jsTestDirectory = readFile(exerciseDir, "test/javascript");
            for (File jsTest : jsTestDirectory.listFiles()) {
                jsTestCode.add(readFileAsString(jsTestDirectory, jsTest.getName()));
            }
            List<String> pythonTestCode = new ArrayList<>();
            File pythonTestDirectory = readFile(exerciseDir, "test/python");
            for (File pythonTest : pythonTestDirectory.listFiles()) {
                pythonTestCode.add(readFileAsString(pythonTestDirectory, pythonTest.getName()));
            }
            Exercise exercise = new Exercise(
                    metaInfo.get("title").textValue().toLowerCase().replaceAll(" ", "-"),
                    metaInfo.get("title").textValue(),
                    metaInfo.get("description").textValue(),
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

    private static String readFileAsString(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    private static String readFileAsString(File exerciseFile, String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(exerciseFile.getAbsolutePath() + "/" + path)));
    }

    private static File readFile(File exerciseFile, String path) {
        return Paths.get(exerciseFile.getAbsolutePath() + "/" + path).toFile();
    }

}
