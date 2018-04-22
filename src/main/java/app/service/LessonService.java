package app.service;

import app.exception.NotFoundException;
import app.model.Lesson;
import app.util.FirebaseUtil;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class LessonService {

    public static Lesson getLesson(String lessonId) {
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

    public static void saveLesson(Lesson lesson) {
        FirebaseUtil.synchronizeWrite("lessons/" + lesson.getId(), lesson);
    }

    public static void deleteLesson(String lessonId) {
        FirebaseUtil.synchronizeWrite("lessons/" + lessonId, null);
    }

}
