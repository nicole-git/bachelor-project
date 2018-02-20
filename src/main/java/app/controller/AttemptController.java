package app.controller;

import app.model.Attempt;
import app.model.CodeRunningInput;
import app.model.CodeRunningResult;
import app.util.FirebaseUtil;

import java.util.UUID;

public class AttemptController {

    public static void registerAttempt(String userId, CodeRunningInput input, CodeRunningResult result) {
        Attempt attempt = new Attempt(
                System.currentTimeMillis(),
                UUID.randomUUID().toString(),
                userId,
                input.getExerciseId(),
                input.getLanguage(),
                input.getCode(),
                result.getPercentageCorrect()
        );
        FirebaseUtil.synchronizeWrite("attempts/" + attempt.getId(), attempt);
    }
}
