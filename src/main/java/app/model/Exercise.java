package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data // creates getters, setters, toString, equals, and hash
@NoArgsConstructor // required by firebase
@AllArgsConstructor // required to create objects
public class Exercise {
    private String id;
    private String title;
    private String description;
    private String instructions;
    private Map<String, String> startCode;
    private Map<String, List<String>> testCode;

    public Map<String, String> getStartCode() {
        if (startCode == null) {
            startCode = new HashMap<>();
        }
        return startCode;
    }

    public Map<String, List<String>> getTestCode() {
        if (testCode == null) {
            testCode = new HashMap<>();
        }
        return testCode;
    }
}