package app.controller;

import app.model.Lesson;
import app.service.LessonService;
import io.javalin.Context;

import java.util.ArrayList;
import java.util.UUID;

public class LessonController {

    public static void getLessons(Context ctx) {
        ctx.json(LessonService.getLessons());
    }

    public static void createLesson(Context ctx) {
        Lesson lessonInput = ctx.bodyAsClass(Lesson.class);
        Lesson newLesson = new Lesson(
                UUID.randomUUID().toString(),
                lessonInput.getTitle(),
                lessonInput.getText(),
                lessonInput.getDifficulty(),
                new ArrayList<>()
        );
        LessonService.saveLesson(newLesson);
        ctx.json(newLesson);
    }

    public static void getLesson(Context ctx) {
        ctx.json(LessonService.getLesson(ctx.param(":lesson-id")));
    }

    public static void deleteLesson(Context ctx) {
        LessonService.deleteLesson(ctx.param(":lesson-id"));
    }

    public static void updateLesson(Context ctx) {
        Lesson userInput = ctx.bodyAsClass(Lesson.class);
        Lesson lessonToBeUpdated = LessonService.getLesson(ctx.param(":lesson-id"));
        lessonToBeUpdated.setTitle(userInput.getTitle());
        lessonToBeUpdated.setText(userInput.getText());
        lessonToBeUpdated.setDifficulty(userInput.getDifficulty());
        LessonService.saveLesson(lessonToBeUpdated);
    }
}
