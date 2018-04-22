package app.util;

import app.model.CodeRunningResult;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.jruby.embed.jsr223.JRubyEngineFactory;
import org.python.jsr223.PyScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScriptService {

    static {
        // javascript is already registered
        new ScriptEngineManager().registerEngineName("python", new PyScriptEngineFactory());
        new ScriptEngineManager().registerEngineName("ruby", new JRubyEngineFactory());
        new ScriptEngineManager().registerEngineName("groovy", new GroovyScriptEngineFactory());
    }

    public static CodeRunningResult runScript(String language, String userCode) {
        try {
            // Create writers
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            StringWriter errorStringWriter = new StringWriter();
            PrintWriter errorPrintWriter = new PrintWriter(errorStringWriter);

            // Do engine work
            ScriptEngine engine = getEngine(language);
            engine.getContext().setErrorWriter(errorPrintWriter);
            engine.getContext().setWriter(printWriter);
            engine.eval(userCode);

            // Close writers
            printWriter.close();
            errorPrintWriter.close();
            stringWriter.close();
            errorStringWriter.close();

            // return result
            return new CodeRunningResult(0, stringWriter.toString());
        } catch (Throwable t) {
            return new CodeRunningResult(0, t.toString());
        }
    }

    public static CodeRunningResult runScriptWithTest(String language, String userCode, Map<String, List<String>> languageTests) {
        double percentageCorrect = 0;
        String percentage = "0.00";
        try {
            ScriptEngine engine = getEngine(language);
            engine.eval(userCode);
            int numberOfTestsPassed = 0;
            List<String> tests = languageTests.get(language); // get test-list for current language
            for (String test : tests) {
                if ((boolean) engine.eval(test)) { // test passed
                    numberOfTestsPassed = numberOfTestsPassed + 1;
                    percentageCorrect = (double) numberOfTestsPassed / tests.size();
                    percentage = new DecimalFormat("#.00").format(percentageCorrect * 100);
                }
            }
            if (percentageCorrect == 1.0) {
                return new CodeRunningResult(1.0, "Your solution is correct, good job!");
            }
            return new CodeRunningResult(percentageCorrect, "Your solution is only " + percentage + "% correct, try again.");
        } catch (Throwable t) {
            return new CodeRunningResult(percentageCorrect, "Your solution is " + percentage + "% correct, but an error occurred: " + formatStackTrace(t));
        }
    }

    private static ScriptEngine getEngine(String language) throws ScriptException {
        String languageName = language.toLowerCase();
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(languageName);
        engine.getContext().setWriter(new PrintWriter(new StringWriter())); // mute engine by default
        if ("javascript".equals(languageName)) { // add console.log to js
            engine.eval("var console = { log: print };");
        }
        return engine;
    }

    private static String formatStackTrace(Throwable t) {
        return Arrays.stream(t.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.joining("\n"));
    }

}
