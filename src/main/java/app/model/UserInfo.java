package app.model;

import java.util.Map;

public class UserInfo {

    public String userId;
    public Map<String, Boolean> solvedExercises;

    // Necessary for object-mapping
    public UserInfo() {
    }

    public UserInfo(String userId, Map<String, Boolean> solvedExercises) {
        this.userId = userId;
        this.solvedExercises = solvedExercises;
    }

    public String getUserId() {
        return userId;
    }

    public Map<String, Boolean> getSolvedExercises() {
        return solvedExercises;
    }

}
