package model;

import java.util.Map;

public class Exercise {

    public final String id;
    public final String title;
    public final String description;
    public final String instructions;
    public final Map<Language, String> testCode;

    public Exercise(String title, String description, String instructions, Map<Language, String> testCode) {
        this.id = title.toLowerCase().replaceAll(" ", "-"); // todo: reconsider
        this.title = title;
        this.description = description;
        this.instructions = instructions;
        this.testCode = testCode;
    }

    // These getters are required by Velocity template engine
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

    public Map<Language, String> getTestCode() {
        return testCode;
    }

}
