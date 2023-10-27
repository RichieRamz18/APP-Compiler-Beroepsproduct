package nl.han.ica.icss.checker;

import nl.han.ica.icss.Pipeline;
import nl.han.ica.icss.ast.ASTNode;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckerTest {
    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorContent = new ByteArrayOutputStream();
    private final PrintStream originalOutput = System.out;
    private final PrintStream originalError = System.err;
    private Pipeline pipeline;



    private String readFile(String resource) throws IOException {
        File file = new File(String.format("./src/main/resources/%s", resource));
        InputStream inputStream = new FileInputStream(file);
        CharStream charStream = CharStreams.fromStream(inputStream);
        return charStream.toString().replaceAll("\r\n", "\n");
    }

    @BeforeEach
    public void setUpStreams() {
        pipeline = new Pipeline();
    }


    /** First all the level files, which have correct semantics, are tested
     *  Then the files with incorrect semantics are tested
     */
    @Test
    void testLevel0PassesCheckerSuccesfully() throws IOException {
        pipeline.parseString(this.readFile("level0.icss"));
        boolean success = pipeline.check();
        assertTrue(success, "The checker should not give any errors");
    }

    @Test
    void testLevel1PassesCheckerSuccesfully() throws IOException {
        pipeline.parseString(this.readFile("level1.icss"));
        boolean success = pipeline.check();
        assertTrue(success, "The checker should not give any errors");
    }

    @Test
    void testLevel2PassesCheckerSuccesfully() throws IOException {
        pipeline.parseString(this.readFile("level2.icss"));
        boolean success = pipeline.check();
        assertTrue(success, "The checker should not give any errors");
    }

    @Test
    void testLevel3PassesCheckerSuccesfully() throws IOException {
        pipeline.parseString(this.readFile("level3.icss"));
        boolean success = pipeline.check();
        assertTrue(success, "The checker should not give any errors");
    }

//    @Test
//    void testCheckCH01() throws IOException {
//
//    }
}

