package com.leetylarry.theo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {

    private static final Map<String, TokenType> keywords;

    static {
        keywords = new HashMap<>();
        keywords.put("and", TokenType.AND);
        keywords.put("class", TokenType.CLASS);
        keywords.put("else", TokenType.ELSE);
        keywords.put("false", TokenType.FALSE);
        keywords.put("for", TokenType.FOR);
        keywords.put("if", TokenType.IF);
        keywords.put("nil", TokenType.NIL);
        keywords.put("or", TokenType.OR);
        keywords.put("print", TokenType.PRINT);
        keywords.put("return", TokenType.RETURN);
        keywords.put("super", TokenType.SUPER);
        keywords.put("this", TokenType.THIS);
        keywords.put("true", TokenType.TRUE);
        keywords.put("while", TokenType.WHILE);
        keywords.put("var", TokenType.VAR);
    }
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

        tokens.add(new Token(TokenType.EOF, "",  line, null));
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
                addToken(TokenType.SEMI_COLON);
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
            case '/':
                if (match('/')) {
                    while (peek() != '\n' && !isAtEnd()) {
                        advanceCharacter();
                    }
                } else {
                    addToken(TokenType.SLASH);
                }
                break;
            case ' ':
            case '\r':
            case '\t':
                break;
            case '\n':
                line++;
                break;
            default:
                if (isAlpha(currentCharacter)) {
                    handleIdentifier();
                } else if (isNumeric(currentCharacter)) {
                    handleNumber();
                } else {
                    System.err.println("Error on line: " + line);
                }
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
        tokens.add(new Token(tokenType, text, line, literal));
    }

    private boolean match(char expectedCharacter) {
        if (isAtEnd() || sourceCode.charAt(current) != expectedCharacter) {
            return false;
        }

        current++;
        return true;
    }

    private boolean isNumeric(char currentCharacter) {
        return currentCharacter >= '0' && currentCharacter <= '9';
    }

    private boolean isAlpha(char currentCharacter) {
        return (currentCharacter >= 'a' && currentCharacter <= 'z') ||
                (currentCharacter >= 'A' && currentCharacter <= 'Z') ||
                (currentCharacter == '_');
    }

    private boolean isAlphaNumeric(char currentCharacter) {
        return isAlpha(currentCharacter) || isNumeric(currentCharacter);
    }

    private void handleIdentifier() {
        while (isAlphaNumeric(peek())) {
            advanceCharacter();
        }

        String text = sourceCode.substring(start, current);
        TokenType tokenType = keywords.get(text);

        if (tokenType == null) {
            tokenType = TokenType.IDENTIFIER;
        }

        addToken(tokenType);
    }

    private void handleNumber() {

        while (isNumeric(peek())) {
            advanceCharacter();
        }

        if (peek() == '.' && isNumeric(peekNext())) {
            advanceCharacter();

            while (isNumeric(peek())) {
                advanceCharacter();
            }
        }

        addToken(TokenType.NUMBER, Double.parseDouble(sourceCode.substring(start, current)));
    }

    private char peek() {
        if (isAtEnd()) {
            return '\0';
        }

        return sourceCode.charAt(current);
     }

     private char peekNext() {
        if (current + 1 >= length) {
            return '\0';
        }

        return sourceCode.charAt(current + 1);
     }

}
