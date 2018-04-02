package app.controller;

import app.exception.NotFoundException;
import app.model.Lesson;
import app.util.FirebaseUtil;
import com.google.firebase.database.DataSnapshot;
import io.javalin.Context;

import java.util.ArrayList;
import java.util.List;

public class LessonController {

    public static Lesson getLesson(String lessonId) throws NotFoundException {
        Lesson lesson = FirebaseUtil.synchronizeRead("lessons/" + lessonId).getValue(Lesson.class);
        if (lesson == null) {
            throw new NotFoundException();
        }
        return lesson;
    }

    public static List<Lesson> getLessons() {
        List<Lesson> lessons = new ArrayList<>();
        for (DataSnapshot child : FirebaseUtil.synchronizeRead("lessons").getChildren()) {
            lessons.add(child.getValue(Lesson.class));
        }
        return lessons;
    }

    public static void deleteLesson(String lessonId) {
        FirebaseUtil.synchronizeWrite("lessons/" + lessonId, null);
    }
}
