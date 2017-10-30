package model;

import java.util.Map;

public class Exercise {

    public String id;
    public String title;
    public String description;
    public String instructions;
    public Map<Language, String> testCode;

    public Exercise(String title, String description, String instructions, Map<Language, String> testCode) {
        this.id = title.toLowerCase().replaceAll(" ", "-"); // todo: reconsider
        this.title = title;
        this.description = description;
        this.instructions = instructions;
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

    public Map<Language, String> getTestCode() {
        return testCode;
    }

}
