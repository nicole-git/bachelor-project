package model;

public class LanguageViewModel {
    private String name;
    private String displayName;

    public LanguageViewModel(Language language, String displayName) {
        this.name = language.name();
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }
}
