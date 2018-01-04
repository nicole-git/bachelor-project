package app.viewmodel;

import app.model.Exercise;
import app.model.UserInfo;
import java.util.Map;

public class ExerciseVm {

    public String id;
    public String title;
    public String description;
    public String instructions;
    public Map<String, String> startCode;
    public boolean solved;

    public ExerciseVm(Exercise exercise, UserInfo userInfo) {
        this.id = exercise.id;
        this.title = exercise.title;
        this.description = exercise.description;
        this.instructions = exercise.instructions;
        this.startCode = exercise.startCode;
        this.solved = userInfo.exerciseToSolved.get(exercise.id);
    }
}

