package util;

import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.jruby.embed.jsr223.JRubyEngineFactory;
import org.python.jsr223.PyScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ScriptService {

    static {
        // javascript is already registered
        new ScriptEngineManager().registerEngineName("python", new PyScriptEngineFactory());
        new ScriptEngineManager().registerEngineName("ruby", new JRubyEngineFactory());
        new ScriptEngineManager().registerEngineName("groovy", new GroovyScriptEngineFactory());
    }

    public static String runScript(String language, String script) {

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        StringWriter errorStringWriter = new StringWriter();
        PrintWriter errorPrintWriter = new PrintWriter(errorStringWriter);

        try {
            // create a script engine manager
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName(language);

            if (engine == null) {
                throw new RuntimeException("Language not supported");
            }

            // create a language engine
            engine.getContext().setErrorWriter(errorPrintWriter);
            engine.getContext().setWriter(printWriter);

            if ("javascript".equals(language)) {
                script = "var console = { log: print };" + script; // enable console
            }

            engine.eval(script); // evaluate code from String

            printWriter.close();
            errorPrintWriter.close();
            stringWriter.close();
            errorStringWriter.close();

            return stringWriter.toString();
        } catch (ScriptException e) {
            return "Failed to run code";
        } catch (Exception e) {
            return "System error...";
        }
    }

}
