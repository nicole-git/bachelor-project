package app.model;

import java.util.Map;

public class UserInfo {

    public String userId;
    public Map<String, Boolean> exerciseToSolved;
    public Map<String, Integer> exerciseToAttempts;

    // Necessary for object-mapping
    public UserInfo() {
    }

    public UserInfo(String userId, Map<String, Boolean> exerciseToSolved, Map<String, Integer> exerciseToAttempts) {
        this.userId = userId;
        this.exerciseToSolved = exerciseToSolved;
        this.exerciseToAttempts = exerciseToAttempts;
    }

    public String getUserId() {
        return userId;
    }

    public Map<String, Boolean> getExerciseToSolved() {
        return exerciseToSolved;
    }

    public Map<String, Integer> getExerciseToAttempts() {
        return exerciseToAttempts;
    }
}
