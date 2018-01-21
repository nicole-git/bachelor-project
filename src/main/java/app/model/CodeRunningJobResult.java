package app.model;

public class CodeRunningJobResult {
    public boolean isCorrect;
    public double percentageCorrect;
    public String message;

    public CodeRunningJobResult(boolean isCorrect, double percentageCorrect, String message) {
        this.isCorrect = isCorrect;
        this.percentageCorrect = percentageCorrect;
        this.message = message;
    }
}

