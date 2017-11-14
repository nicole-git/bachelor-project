package app.model;

import java.util.Map;

public class Exercise {

    public String id;
    public String title;
    public String description;
    public String instructions;
    public Map<String, String> startCode;
    public Map<String, String> testCode;

    public Exercise() {
    }

    public Exercise(String title, String description, String instructions, Map<String, String> startCode, Map<String, String> testCode) {
        this.id = title.toLowerCase().replaceAll(" ", "-"); // todo: reconsider
        this.title = title;
        this.description = description;
        this.instructions = instructions;
        this.startCode = startCode;
        this.testCode = testCode;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructions() {
        return instructions;
    }

    public Map<String, String> getStartCode() {
        return startCode;
    }

    public Map<String, String> getTestCode() {
        return testCode;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", instructions='" + instructions + '\'' +
                ", startCode=" + startCode +
                ", testCode=" + testCode +
                '}';
    }
}
