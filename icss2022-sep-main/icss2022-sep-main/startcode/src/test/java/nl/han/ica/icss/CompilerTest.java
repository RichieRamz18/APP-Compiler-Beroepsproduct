package nl.han.ica.icss;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompilerTest {
    //Testklasse om alle onderdelen van de compiler te testen met de aangeleverde level-bestanden en eigen input testbestanden

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
    public void testLevel0() throws IOException {
        String output = this.getCSSOutput("level0.icss");
        String expected = this.readFile("level0.css");
        assertEquals(expected, output);
    }

    @Test
    public void testLevel1() throws IOException {
        String output = this.getCSSOutput("level1.icss");
        String expected = this.readFile("level1.css");
        assertEquals(expected, output);
    }

    @Test
    public void testLevel2() throws IOException {
        String output = this.getCSSOutput("level2.icss");
        String expected = this.readFile("level2.css");
        assertEquals(expected, output);
    }

    @Test
    public void testLevel3() throws IOException {
        String output = this.getCSSOutput("level3.icss");
        String expected = this.readFile("level3.css");
        assertEquals(expected, output);
    }

    @Test
    public void testLevel0Inputbestand() throws IOException {
        String output = this.getCSSOutput("level0inputbestand.icss");
        String expected = this.readFile("level0inputbestand.css");
        assertEquals(expected, output);
    }

    @Test
    public void testLevel1Inputbestand()throws IOException {
        String output = this.getCSSOutput("level1inputbestand.icss");
        String expected = this.readFile("level1inputbestand.css");
        assertEquals(expected, output);
    }

    @Test
    public void testLevel2Inputbestand() throws IOException {
        String output = this.getCSSOutput("level2inputbestand.icss");
        String expected = this.readFile("level2inputbestand.css");
        assertEquals(expected, output);
    }

    @Test
    public void testLevel3Inputbestand() throws IOException {
        String output = this.getCSSOutput("level3inputbestand.icss");
        String expected = this.readFile("level3inputbestand.css");
        assertEquals(expected, output);
    }

}
