package app.model;

import app.service.ExerciseService;
import lombok.Data;

import java.util.List;

@Data // creates getters, setters, toString, equals, and hash
public class ExerciseInfo {

    private String exerciseId;
    private String exerciseTitle;
    private int totalAttempts;
    private long totalSolves;
    private double averageAttemptsBeforeSolve;

    public ExerciseInfo(String exerciseId, List<UserInfo> userInfoList) {

        this.exerciseTitle = ExerciseService.getExercise(exerciseId).getTitle();
        this.exerciseId = exerciseId;

        // map userInfo to "number of attempts user has" for the given exercise, then sum these numbers
        this.totalAttempts = userInfoList.stream().map(userInfo ->
                userInfo.getExerciseToAttempts().get(exerciseId)
        ).reduce(0, Integer::sum);

        // map userInfo to "if user has solved exercise" for the given exercise, then count number of solves
        this.totalSolves = userInfoList.stream().map(userInfo ->
                userInfo.getExerciseToSolved().get(exerciseId)
        ).filter(solved -> solved == true).count();

        this.averageAttemptsBeforeSolve = totalSolves != 0 ? (double) totalAttempts / totalSolves : 0;

    }
}
