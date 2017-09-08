import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class JavaScriptUtil {

    public static String runScript(String script) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        StringWriter swError = new StringWriter();
        PrintWriter pwError = new PrintWriter(swError);

        try {
            // create a script engine manager
            ScriptEngineManager factory = new ScriptEngineManager();

            // create a JavaScript engine
            ScriptEngine engine = factory.getEngineByName("nashorn");
            engine.getContext().setErrorWriter(pwError);
            engine.getContext().setWriter(pw);

            // evaluate JavaScript code from String
            engine.eval("var console = { log: print };" + script); // enable console.log

            return sw.toString();
        } catch (ScriptException e) {
            return null;
        }
    }

}
