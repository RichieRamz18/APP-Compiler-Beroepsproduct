package nl.han.ica.icss.transforms;

import nl.han.ica.icss.Pipeline;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class EvaluatorTest {
    // Testklasse om de transformaties te testen met eigen testbestanden
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

    @Test
    public void testTransformTR01Expressions() throws IOException {
        String output = this.getCSSOutput("TR01testbestand1.icss");
        String expected = this.readFile("TR01testbestand1.css");
        assertEquals(expected, output);
    }

    

}