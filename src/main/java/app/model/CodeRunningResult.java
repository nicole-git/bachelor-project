package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // creates getters, setters, toString, equals, and hash
@AllArgsConstructor // required to create objects
public class CodeRunningResult {
    private double percentageCorrect;
    private String message;
}

