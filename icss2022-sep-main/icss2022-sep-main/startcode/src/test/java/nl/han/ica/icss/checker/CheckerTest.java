package nl.han.ica.icss.checker;

import nl.han.ica.icss.Pipeline;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckerTest {
    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorContent = new ByteArrayOutputStream();
    private final PrintStream originalOutput = System.out;
    private final PrintStream originalError = System.err;


    private String readFile(String resource) throws IOException {
        File file = new File(String.format("./src/main/resources/%s", resource));
        InputStream inputStream = new FileInputStream(file);
        CharStream charStream = CharStreams.fromStream(inputStream);
        return charStream.toString().replaceAll("\r\n", "\n");
    }

    @BeforeEach
    public void setUpStreams(){
        System.setOut(new PrintStream(outputContent));
        System.setErr(new PrintStream(errorContent));
    }

    /* First all the level files, which have correct semantics, are tested
    *  Then the files with incorrect semantics are tested
    */
    @Test
    void testLevel0PassesCheckerSuccesfully() throws IOException {
        Pipeline pipeline = new Pipeline();
        pipeline.parseString(this.readFile("level0.icss"));
        boolean success = pipeline.check();
        assertTrue(success);
    }

    @Test
    void testLevel1PassesCheckerSuccesfully() throws IOException {
        Pipeline pipeline = new Pipeline();
        pipeline.parseString(this.readFile("level1.icss"));
        boolean success = pipeline.check();
        assertTrue(success);
    }

    @Test
    void testLevel2PassesCheckerSuccesfully() throws IOException {
        Pipeline pipeline = new Pipeline();
        pipeline.parseString(this.readFile("level2.icss"));
        boolean success = pipeline.check();
        assertTrue(success);
    }

    @Test
    void testLevel3PassesCheckerSuccesfully() throws IOException {
        Pipeline pipeline = new Pipeline();
        pipeline.parseString(this.readFile("level3.icss"));
        boolean success = pipeline.check();
        assertTrue(success);
    }

    @Test
    void testCheckCH01() throws IOException {
        Pipeline pipeline = new Pipeline();
        Checker checker = new Checker();

        pipeline.parseString(this.readFile("CH01testbestand.icss"));
        boolean success = pipeline.check();
        assertFalse(success, "The checker should fail on CH01testbestand.icss");

        String consoleOutput = outContent.toString
    }



}