package nl.han.ica.icss.checker;

import nl.han.ica.icss.Pipeline;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckerTest {
    private Pipeline pipeline;

    private String readFile(String resource) throws IOException {
        File file = new File(String.format("./src/main/resources/%s", resource));
        InputStream inputStream = new FileInputStream(file);
        CharStream charStream = CharStreams.fromStream(inputStream);
        return charStream.toString().replaceAll("\r\n", "\n");
    }

    @BeforeEach
    public void setUp() {
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

    /**
     * The following tests are for the CH01, CH02, CH03, CH05 and CH06 checks with files that have incorrect semantics
     */
    @Test
    void testCheckCH01OnUndefinedVariable() throws IOException {
        pipeline.parseString(this.readFile("CH01testbestand.icss"));
        boolean success = pipeline.check();
        assertFalse(success, "The checker should give an error because of an undefined variable");
    }
    @Test
    void testCheckCH02OnEqualOperationTypes() throws IOException {
        pipeline.parseString(this.readFile("CH02testbestand.icss"));
        boolean success = pipeline.check();
        assertFalse(success, "The checker should give an error because of an unequal operation type");
    }

    @Test
    void testCheckCH03OnUseOfColorsInOperation() throws IOException {
        pipeline.parseString(this.readFile("CH03testbestand.icss"));
        boolean success = pipeline.check();
        assertFalse(success, "The checker should give an error because of the use of colors in an operation");
    }

    @Test
    void testCheckCH04OnInvalidDeclarations() throws IOException {
        pipeline.parseString(this.readFile("CH04testbestand.icss"));
        boolean success = pipeline.check();
        assertFalse(success, "The checker should give an error because of an invalid declaration");
    }

    @Test
    void testCheckCH05OnWrongUseOfConditionInIfClause() throws IOException {
        pipeline.parseString(this.readFile("CH05testbestand.icss"));
        boolean success = pipeline.check();
        assertFalse(success, "The checker should give an error because of the wrong use of a condition in an if clause");
    }

    @Test
    void testCheckCH06OnUseOfVariablesWithinOwnScope() throws IOException{
        pipeline.parseString(this.readFile("CH06testbestand.icss"));
        boolean success = pipeline.check();
        assertFalse(success, "The checker should give an error because of the use of variables within their own scope");
    }
}

