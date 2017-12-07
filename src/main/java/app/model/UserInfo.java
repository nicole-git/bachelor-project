package app.model;

import java.util.Map;

public class UserInfo {

    public String userId;
    public Map<String, Boolean> solvedExercises;

    public UserInfo() {
    }

    public UserInfo(String userId, Map<String, Boolean> solvedExercises) {
        this.userId = userId;
        this.solvedExercises = solvedExercises;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
            "userId='" + userId + '\'' +
            ", solvedExercises=" + solvedExercises +
            '}';
    }
}
