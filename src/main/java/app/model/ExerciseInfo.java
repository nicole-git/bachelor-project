package app.model;

import java.util.List;

public class ExerciseInfo {

    String exerciseId;
    int totalAttempts;
    long totalSolves;
    double averageAttemptsBeforeSolve;

    public ExerciseInfo(String exerciseId, List<UserInfo> userInfoList) {

        this.exerciseId = exerciseId;

        // map userInfo to "number of attempts user has" for the given exercise, then sum these numbers
        this.totalAttempts = userInfoList.stream().map(userInfo ->
            userInfo.exerciseToAttempts.get(exerciseId)
        ).reduce(0, Integer::sum);

        // map userInfo to "if user has solved exercise" for the given exercise, then count number of solves
        this.totalSolves = userInfoList.stream().map(userInfo ->
            userInfo.exerciseToSolved.get(exerciseId)
        ).filter(solved -> solved == true).count();

        this.averageAttemptsBeforeSolve = totalSolves != 0 ? (double) totalAttempts / totalSolves : 0;

    }

    public String getExerciseId() {
        return exerciseId;
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public long getTotalSolves() {
        return totalSolves;
    }

    public double getAverageAttemptsBeforeSolve() {
        return averageAttemptsBeforeSolve;
    }
}
