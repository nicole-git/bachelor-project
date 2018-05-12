package app.controller;

import app.model.Lesson;
import app.service.LessonService;
import app.service.UserService;
import io.javalin.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LessonController {

    public static void getLessons(Context ctx) {
        boolean isAdmin = UserService.getSessionInfo(ctx).isAdmin();
        List<Lesson> lessons = LessonService.getLessons().stream()
            .filter(l -> isAdmin || l.isPublished()) // get lesson if user is admin, or if it's published
            .collect(Collectors.toList());
        ctx.json(lessons);
    }

    public static void createLesson(Context ctx) {
        Lesson lessonInput = ctx.bodyAsClass(Lesson.class);
        Lesson newLesson = new Lesson(
                UUID.randomUUID().toString(),
                lessonInput.getTitle(),
                lessonInput.getText(),
                lessonInput.getDifficulty(),
                false,
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
        lessonToBeUpdated.setPublished(userInput.isPublished());
        LessonService.saveLesson(lessonToBeUpdated);
    }
}
