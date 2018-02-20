package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // creates getters, setters, toString, equals, and hash
@NoArgsConstructor // required by firebase
@AllArgsConstructor // required to create objects
public class Attempt {
    long dateTime;
    String id;
    String userId;
    String exerciseId;
    String language;
    String userCode;
    double percentageCorrect;
}
