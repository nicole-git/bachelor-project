package app.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import lombok.Data;

@Data
public class PivotAttempt {
    String userId;
    String exerciseId;
    String language;
    String percentageCorrect;
    String date;

    public PivotAttempt(Attempt attempt) {
        this.userId = attempt.userId;
        this.exerciseId = attempt.exerciseId;
        this.language = attempt.language;
        this.percentageCorrect = new DecimalFormat("#").format(attempt.percentageCorrect * 100) + "%";
        SimpleDateFormat formatter = new SimpleDateFormat("dd. MMM, yy", Locale.US);
        this.date = formatter.format(new Date(attempt.dateTime));
    }
}
