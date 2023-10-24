package nl.han.ica.icss.checker;

import nl.han.ica.icss.Pipeline;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class CheckerTest {
    private String readFile(String resource) throws IOException {
        File file = new File(String.format("./src/main/resources/%s", resource));
        InputStream inputStream = new FileInputStream(file);
        CharStream charStream = CharStreams.fromStream(inputStream);
        return charStream.toString().replaceAll("\r\n", "\n");
    }

    /* First all the level files, which are correct, are tested
    *
    */
    @Test
    void testLevel0PassesCheckerSuccesfully() throws IOException {
        Pipeline pipeline = new Pipeline();
        pipeline.parseString(this.readFile("level0.icss"));
        boolean success = pipeline.check();
        assertTrue(success);
    }

    @Test
    void testLevel0PassesCheckerSuccesfully() throws IOException {
        Pipeline pipeline = new Pipeline();
        pipeline.parseString(this.readFile("level0.icss"));
        boolean success = pipeline.check();
        assertTrue(success);
    }

    @Test
    void testLevel0PassesCheckerSuccesfully() throws IOException {
        Pipeline pipeline = new Pipeline();
        pipeline.parseString(this.readFile("level0.icss"));
        boolean success = pipeline.check();
        assertTrue(success);
    }

    @Test
    void testLevel0PassesCheckerSuccesfully() throws IOException {
        Pipeline pipeline = new Pipeline();
        pipeline.parseString(this.readFile("level0.icss"));
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
    }



}