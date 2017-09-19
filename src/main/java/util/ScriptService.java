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

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        StringWriter swError = new StringWriter();
        PrintWriter pwError = new PrintWriter(swError);

        try {
            // create a script engine manager
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName(language);

            if (engine == null) {
                throw new RuntimeException("Language not supported");
            }

            // create a language engine
            engine.getContext().setErrorWriter(pwError);
            engine.getContext().setWriter(pw);

            // evaluate code from String
            if ("javascript".equals(language)) {
                script = "var console = { log: print };" + script;
            }
            engine.eval(script);

            pw.close();
            pwError.close();
            sw.close();
            swError.close();

            return sw.toString();
        } catch (ScriptException e) {
            return "Failed to run code";
        } catch (Exception e) {
            return "System error...";
        }
    }

}
