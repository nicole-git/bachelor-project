package app.controller;

import app.exception.NotFoundException;
import app.model.Lesson;
import app.util.FirebaseUtil;
import com.google.firebase.database.DataSnapshot;
import io.javalin.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LessonController {

    public static void getLessons(Context ctx) {
        List<Lesson> lessons = new ArrayList<>();
        for (DataSnapshot child : FirebaseUtil.synchronizeRead("lessons").getChildren()) {
            lessons.add(child.getValue(Lesson.class));
        }
        ctx.json(lessons);
    }

    public static void createLesson(Context ctx) {
        Lesson lessonInput = ctx.bodyAsClass(Lesson.class);
        Lesson newLesson = new Lesson(
                UUID.randomUUID().toString(),
                lessonInput.getTitle(),
                lessonInput.getText(),
                new ArrayList<>()
        );
        FirebaseUtil.synchronizeWrite("lessons/" + newLesson.getId(), newLesson);
        ctx.json(newLesson);
    }

    public static void getLesson(Context ctx) {
        ctx.json(getLesson(ctx.param("lesson-id")));
    }

    public static Lesson getLesson(String lessonId) {
        Lesson lesson = FirebaseUtil.synchronizeRead("lessons/" + lessonId).getValue(Lesson.class);
        if (lesson == null) {
            throw new NotFoundException();
        }
        return lesson;
    }

    public static void deleteLesson(Context ctx) {
        FirebaseUtil.synchronizeWrite("lessons/" + ctx.param("lesson-id"), null);
    }
}
