package com.leetylarry.theo;

import com.leetylarry.theo.expressions.Expr;
import com.leetylarry.theo.statements.Statement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class InterpreterTest {

    private Interpreter interpreter;
    private Scanner scanner;

    @BeforeEach
    void setup() {
        interpreter = new Interpreter();
    }

    @Test
    void singleCharacters() throws IOException {
        String sourceCode = SourceCodeReader.readFileToSourceCode("/home/ty/JDev/Theodore-Java/src/main/resources/parser_chars.theo");
        scanner = new Scanner(sourceCode);

        List<Token> tokens = scanner.scanTokens();
        Parser parser = new Parser(tokens);

        List<Statement> expr = parser.parse();

        interpreter.interpret(expr);

    }

}
