package app.viewmodel;

import app.model.Language;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data // creates getters, setters, toString, equals, and hash
@AllArgsConstructor // required to create objects
public class LanguageVm {

    private String name;
    private String displayName;

    public static List<LanguageVm> supportedLanguages = ImmutableList.of(
            new LanguageVm(Language.JAVASCRIPT, "JavaScript"),
            new LanguageVm(Language.PYTHON, "Python"),
            new LanguageVm(Language.RUBY, "Ruby"),
            new LanguageVm(Language.GROOVY, "Groovy")
    );

}
