package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // getters and setters, equals, hash, toString
@AllArgsConstructor // normal constructor
@NoArgsConstructor // required by firebase
public class Lesson {
    private String id;
    private String title;
    private String text;
    private List<String> exerciseIds;
}
