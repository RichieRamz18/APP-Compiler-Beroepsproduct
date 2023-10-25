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
    private Checker checker;


    private String readFile(String resource) throws IOException {
        File file = new File(String.format("./src/main/resources/%s", resource));
        InputStream inputStream = new FileInputStream(file);
        CharStream charStream = CharStreams.fromStream(inputStream);
        return charStream.toString().replaceAll("\r\n", "\n");
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputContent));
        System.setErr(new PrintStream(errorContent));
        pipeline = new Pipeline();
        checker = new Checker();
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOutput);
        System.setErr(originalError);
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
        assertTrue(success, "The checker should not give any errors");
    }

//    @Test
//    void testCheckCH01() throws IOException {
//        // Read file and parse it
//        String testFile = this.readFile("CH01testbestand2.icss");
//        pipeline.parseString(testFile);
//
//        // Execute checkUndefinedVariables-function on AST
//        ASTNode rootNode = pipeline.getAST().root;
//        checker.checkUndefinedVariables(rootNode);
//        // Check if the error is correct
//        ASTNode nodeWithExpectedError = findNodeWithExpectedError(rootNode, "Variable LankColor is not defined and can't be used");
//        assertNotNull(nodeWithExpectedError, "The checker should give an error on line 1");
//        assertTrue(nodeWithExpectedError.hasError(), "Error should be set for specific node");
//    }


    /*
     * Recursive method that tries to find an error with the expected error message among the nodes
     * */
    private ASTNode findNodeWithExpectedError(ASTNode node, String expectedError) {
        if (node.hasError() && node.getError().equals(expectedError)) {
            return node;
        } else {
            for (ASTNode child : node.getChildren()) {
                ASTNode result = findNodeWithExpectedError(child, expectedError);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }


}

