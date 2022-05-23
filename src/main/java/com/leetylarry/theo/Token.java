package com.leetylarry.theo;

public class Token {
    final TokenType tokenType;
    final String lexeme;
    final int line;

    public Token(TokenType tokenType, String lexeme, int line) {
        this.tokenType = tokenType;
        this.lexeme = lexeme;
        this.line = line;
    }

    public String toString() {
        return tokenType + " " + lexeme;
    }
}
