package model;

public class Exercise {

    public String id;
    public String title;
    public String description;
    public String instructions;
    public String testCode;
    public String expectedValue;

    public Exercise(String title, String description, String instructions, String testCode, String expectedValue) {
        this.id = title.toLowerCase().replaceAll(" ", "-"); // todo: reconsider
        this.title = title;
        this.description = description;
        this.instructions = instructions;
        this.testCode = testCode;
        this.expectedValue = expectedValue;
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

    public String getTestCode() {
        return testCode;
    }

    public String getExpectedValue() {
        return expectedValue;
    }
}
