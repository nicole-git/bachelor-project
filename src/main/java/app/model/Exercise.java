package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}