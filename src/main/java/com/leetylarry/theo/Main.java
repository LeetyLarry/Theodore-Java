package com.leetylarry.theo;

import com.leetylarry.theo.expressions.ASTPrinter;
import com.leetylarry.theo.expressions.Expr;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String sourceCode = SourceCodeReader.readFileToSourceCode("/home/ty/JDev/Theodore-Java/src/main/resources/parser_chars.theo");
        Scanner scanner = new Scanner(sourceCode);

        List<Token> tokens = scanner.scanTokens();
        Parser parser = new Parser(tokens);

    }
}
