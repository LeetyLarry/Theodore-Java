package com.leetylarry.theo;

import com.leetylarry.theo.expressions.Expr;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    private Scanner scanner;

    @Test
    void singleCharacters() throws IOException {
        String sourceCode = SourceCodeReader.readFileToSourceCode("/home/ty/JDev/Theodore-Java/src/main/resources/single_characters.theo");
        scanner = new Scanner(sourceCode);

        List<Token> tokens = scanner.scanTokens();
        Parser parser = new Parser(tokens);

        Expr expr = parser.parse();
    }

}