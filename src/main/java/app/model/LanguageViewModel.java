package app.model;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class LanguageViewModel {

    private String name;
    private String displayName;

    public LanguageViewModel(String language, String displayName) {
        this.name = language;
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static List<LanguageViewModel> supportedLanguages = ImmutableList.of(
            new LanguageViewModel(Language.JAVASCRIPT, "JavaScript"),
            new LanguageViewModel(Language.PYTHON, "Python"),
            new LanguageViewModel(Language.RUBY, "Ruby"),
            new LanguageViewModel(Language.GROOVY, "Groovy")
    );

}
