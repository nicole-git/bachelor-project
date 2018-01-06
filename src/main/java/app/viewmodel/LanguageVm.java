package app.viewmodel;

import app.model.Language;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class LanguageVm {

    private String name;
    private String displayName;

    public LanguageVm(String language, String displayName) {
        this.name = language;
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static List<LanguageVm> supportedLanguages = ImmutableList.of(
            new LanguageVm(Language.JAVASCRIPT, "JavaScript"),
            new LanguageVm(Language.PYTHON, "Python"),
            new LanguageVm(Language.RUBY, "Ruby"),
            new LanguageVm(Language.GROOVY, "Groovy")
    );

}
