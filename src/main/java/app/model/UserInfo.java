package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data // creates getters, setters, toString, equals, and hash
@AllArgsConstructor // required to create objects
public class UserInfo {
    private String userId;
    private Map<String, Boolean> exerciseToSolved;
    private Map<String, Integer> exerciseToAttempts;
}