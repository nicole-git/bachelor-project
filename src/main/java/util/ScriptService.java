package util;

import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.jruby.embed.jsr223.JRubyEngineFactory;
import org.python.jsr223.PyScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

public class ScriptService {

    static {
        // javascript is already registered
        new ScriptEngineManager().registerEngineName("python", new PyScriptEngineFactory());
        new ScriptEngineManager().registerEngineName("ruby", new JRubyEngineFactory());
        new ScriptEngineManager().registerEngineName("groovy", new GroovyScriptEngineFactory());
    }

    public static String runScript(String language, String userCode) {
        try {

            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            StringWriter errorStringWriter = new StringWriter();
            PrintWriter errorPrintWriter = new PrintWriter(errorStringWriter);

            // create a script engine manager
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName(language);

            if (engine == null) {
                return "Language not supported";
            }

            // create a language engine
            engine.getContext().setErrorWriter(errorPrintWriter);
            engine.getContext().setWriter(printWriter);

            if ("javascript".equals(language)) {
                userCode = "var console = { log: print };" + userCode; // enable console
            }

            engine.eval(userCode); // evaluate code from String

            printWriter.close();
            errorPrintWriter.close();
            stringWriter.close();
            errorStringWriter.close();

            return stringWriter.toString();
        } catch (Throwable t) {
            return t.toString();
        }
    }

    public static String runScriptWithTest(String language, String userCode, String testCode, String expectedValue) {
        String userCodeWithTest = userCode + testCode; // add test-code after user-code
        String result = runScript(language, userCodeWithTest).trim(); // remove line-breaks
        if (expectedValue.equals(result)) {
            return "Your answer is correct. Good job.";
        }
        return "Your answer is incorrect. \nExpected: '" + expectedValue + "' \nActual:   '" + result + "'";
    }

}
