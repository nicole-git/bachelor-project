package model;

public class Exercise {

    public String id;
    public String title;
    public String description;
    public String instructions;
    public String answer;

    public Exercise(String title, String description, String instructions, String answer) {
        this.id = title.toLowerCase().replaceAll(" ", "-"); // todo: reconsider
        this.title = title;
        this.description = description;
        this.instructions = instructions;
        this.answer = answer;
    }
}
