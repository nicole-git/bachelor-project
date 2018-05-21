package app.service;

import app.model.Attempt;
import app.model.CodeRunningInput;
import app.model.CodeRunningResult;
import app.util.FirebaseUtil;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class AttemptService {

    public static void registerAttempt(String userId, CodeRunningInput input, CodeRunningResult result) {
        Attempt attempt = new Attempt(
                System.currentTimeMillis(),
                FirebaseUtil.randomId(),
                userId,
                input.getExerciseId(),
                input.getLanguage(),
                input.getCode(),
                result.getPercentageCorrect()
        );
        FirebaseUtil.synchronizeWrite("attempts/" + attempt.getId(), attempt);
    }

    public static List<Attempt> getAttempts() {
        List<Attempt> attempts = new ArrayList<>();
        for (DataSnapshot child : FirebaseUtil.synchronizeRead("attempts").getChildren()) {
            attempts.add(child.getValue(Attempt.class));
        }
        return attempts;
    }

}
