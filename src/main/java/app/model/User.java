package app.model;

import app.security.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // creates getters, setters, toString, equals, and hash
@NoArgsConstructor // required by firebase
@AllArgsConstructor // required to create objects
public class User {
    private String username;
    private String password;
    private UserRole userRole;
}
