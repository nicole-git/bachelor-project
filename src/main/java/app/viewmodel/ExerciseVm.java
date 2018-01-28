package app.viewmodel;

import app.model.Exercise;
import app.model.UserInfo;
import lombok.Data;

import java.util.Map;

@Data // creates getters, setters, toString, equals, and hash
public class ExerciseVm {

    private String id;
    private String title;
    private String description;
    private String instructions;
    private Map<String, String> startCode;
    private boolean solved;

    public ExerciseVm(Exercise exercise, UserInfo userInfo) {
        this.id = exercise.getId();
        this.title = exercise.getTitle();
        this.description = exercise.getDescription();
        this.instructions = exercise.getInstructions();
        this.startCode = exercise.getStartCode();
        this.solved = userInfo.getExerciseToSolved().get(exercise.getId());
    }
}

