package nl.han.ica.icss;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompilerTest {
    //Testklasse om alle onderdelen van de compiler te testen met testbestanden

    private String readFile(String resource) throws IOException {
        File file = new File(String.format("./src/main/resources/%s", resource));
        InputStream inputStream = new FileInputStream(file);
        CharStream charStream = CharStreams.fromStream(inputStream);
        return charStream.toString().replaceAll("\r\n", "\n");
    }

    private String getCSSOutput(String filename) throws IOException {
        Pipeline pipeline = new Pipeline();
        pipeline.parseString(this.readFile(filename));
        boolean success = pipeline.check();
        assertTrue(success, String.format("Checker should not detect any errors, detected: %s", pipeline.getErrors()));
        pipeline.transform();
        return pipeline.generate();
    }



}
