package app.model;

import app.service.UserService;
import java.util.Map;
import lombok.Data;

@Data
public class StudentExercise {
    private String id;
    private String title;
    private String description;
    private String instructions;
    private Map<String, String> startCode;
    private boolean solved;

    public StudentExercise(Exercise exercise, String username) {
        this.id = exercise.getId();
        this.title = exercise.getTitle();
        this.description = exercise.getDescription();
        this.instructions = exercise.getInstructions();
        this.startCode = exercise.getStartCode();
        this.solved = UserService.getExerciseSolved(username, exercise.getId());
    }
}
