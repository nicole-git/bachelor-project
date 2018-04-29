package app.service;

import app.exception.InvalidLoginException;
import app.model.User;
import app.util.FirebaseUtil;

public class LoginService {

    public static User getUserByUsername(String username) {
        User user = FirebaseUtil.synchronizeRead("users/" + username).getValue(User.class);
        if (user == null) {
            throw new InvalidLoginException();
        }
        return user;
    }

}
