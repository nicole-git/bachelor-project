package model;

public class LanguageViewModel {
    private String name;
    private String displayName;

    public LanguageViewModel(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }
}
