package com.leetylarry.theo;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ScannerTest {

    private Scanner scanner;
    @Test
    void singleCharacters() throws IOException {
        String sourceCode = SourceCodeReader.readFileToSourceCode("/home/ty/JDev/Theodore-Java/src/main/resources/single_characters.theo");
        scanner = new Scanner(sourceCode);

        List<Token> actualTokens = scanner.scanTokens();

        assertEquals(5, actualTokens.size());
    }

    @Test
    void doubleCharacters() throws IOException {
        String sourceCode = SourceCodeReader.readFileToSourceCode("/home/ty/JDev/Theodore-Java/src/main/resources/double_characters.theo");
        scanner = new Scanner(sourceCode);

        List<Token> actualTokens = scanner.scanTokens();

        System.out.println(actualTokens);

        assertEquals(4, actualTokens.size());
    }

}