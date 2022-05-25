package com.leetylarry.theo;

public class Token {
    final TokenType tokenType;
    final String lexeme;
    final int line;
    final Object literal;

    public Token(TokenType tokenType, String lexeme, int line, Object literal) {
        this.tokenType = tokenType;
        this.lexeme = lexeme;
        this.line = line;
        this.literal = literal;
    }

    public String toString() {
        return tokenType + " " + lexeme;
    }
}
