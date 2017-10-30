package model;

import org.python.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.List;

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

    public static List<LanguageViewModel> supportedLanguages = ImmutableList.of(
            new LanguageViewModel(Language.JAVASCRIPT, "JavaScript"),
            new LanguageViewModel(Language.PYTHON, "Python"),
            new LanguageViewModel(Language.RUBY, "Ruby"),
            new LanguageViewModel(Language.JAVASCRIPT, "Groovy")
    );

}
