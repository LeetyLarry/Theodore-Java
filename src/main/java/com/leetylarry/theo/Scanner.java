package com.leetylarry.theo;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
    private final String sourceCode;
    private final List<Token> tokens;
    private final int length;
    private int start;
    private int current;
    private int line;

    public Scanner(String sourceCode) {
        this.sourceCode = sourceCode;
        this.tokens = new ArrayList<>();
        start = 0;
        current = 0;
        line = 1;
        length = sourceCode.length();
    }

    List<Token> scanTokens() {
        while (!isAtEnd()) {

            start = current;
            scanToken();
        }

        tokens.add(new Token(TokenType.EOF, "",  line));
        return tokens;
    }

    private boolean isAtEnd() {
        return current >= sourceCode.length();
    }

    private void scanToken() {
        char currentCharacter = advanceCharacter();

        switch (currentCharacter) {
            case '(':
                addToken(TokenType.LEFT_PAREN);
                break;
            case ')':
                addToken(TokenType.RIGHT_PAREN);
                break;
            case '{':
                addToken(TokenType.LEFT_BRACE);
                break;
            case '}':
                addToken(TokenType.RIGHT_BRACE);
                break;
            case ',':
                addToken(TokenType.COMMA);
                break;
            case '.':
                addToken(TokenType.DOT);
                break;
            case '-':
                addToken(TokenType.MINUS);
                break;
            case '+':
                addToken(TokenType.PLUS);
                break;
            case ';':
                addToken(TokenType.SEMIOCOLON);
                break;
            case '*':
                addToken(TokenType.STAR);
                break;
            case '!':
                addToken(match('=') ? TokenType.EXCLAMATION_EQUAL : TokenType.EXCLAMATION);
                break;
            case '=':
                addToken(match('=') ? TokenType.DOUBLE_EQUAL : TokenType.EQUAL);
                break;
            case '<':
                addToken(match('=') ? TokenType.LESS_THAN_EQUAL : TokenType.LESS_THAN);
                break;
            case '>':
                addToken(match('=') ? TokenType.GREATER_THAN_EQUAL : TokenType.GREATER_THAN);
                break;
            default:
                System.err.println("Error on line: " + line);
        }
    }

    private char advanceCharacter() {
        current++;
        return sourceCode.charAt(current - 1);
    }

    private void addToken(TokenType tokenType) {
        addToken(tokenType, null);
    }

    private void addToken(TokenType tokenType, Object literal) {
        String text = sourceCode.substring(start, current);
        tokens.add(new Token(tokenType, text, line));
    }

    private boolean match(char expectedCharacter) {
        if (isAtEnd() || sourceCode.charAt(current) != expectedCharacter) {
            return false;
        }

        current++;
        return true;
    }
}
