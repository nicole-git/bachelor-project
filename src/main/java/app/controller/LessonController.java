package app.controller;

import app.exception.NotFoundException;
import app.model.Lesson;
import app.util.FirebaseUtil;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class LessonController {

    public static Lesson getLesson(String lessonId) throws NotFoundException {
        for (Lesson lesson : getLessons()) {
            if (lesson.getId().equals(lessonId)) {
                return lesson;
            }
        }
        throw new NotFoundException();
    }

    public static List<Lesson> getLessons() {
        List<Lesson> lessons = new ArrayList<>();
        for (DataSnapshot child : FirebaseUtil.synchronizeRead("lessons").getChildren()) {
            lessons.add(child.getValue(Lesson.class));
        }
        return lessons;
    }

}
