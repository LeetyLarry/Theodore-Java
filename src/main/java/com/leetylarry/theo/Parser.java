package com.leetylarry.theo;

import com.leetylarry.theo.expressions.*;

import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int current = 0;

    Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Expr expr() {
        return equality();
    }

    private Expr equality() {
        Expr expr = comparison();

        while (match(TokenType.EXCLAMATION, TokenType.EXCLAMATION_EQUAL)) {
            Token operator = previous();
            Expr right = comparison();
            expr = new Binary(expr, operator, right);
        }

        return expr;
    }

    private Expr comparison() {
        Expr expr = term();

        while (match(TokenType.LESS_THAN, TokenType.LESS_THAN_EQUAL,
                TokenType.GREATER_THAN, TokenType.GREATER_THAN_EQUAL)) {
            Token operator = previous();
            Expr right = term();
            expr = new Binary(expr, operator, right);
        }

        return expr;
    }

    private Expr term() {
        Expr expr = factor();

        while (match(TokenType.MINUS, TokenType.PLUS)) {
            Token operator = previous();
            Expr right = factor();
            expr = new Binary(expr, operator, right);
        }

        return expr;
    }
    
    private Expr factor() {
        Expr expr = unary();
        
        while (match(TokenType.SLASH, TokenType.STAR)) {
            Token operator = previous();
            Expr right = unary();
            expr = new Binary(expr, operator, right);
        }
        
        return expr;
    }
    
    private Expr unary() {
        if (match(TokenType.EXCLAMATION, TokenType.MINUS)) {
            Token operator = previous();
            Expr right = unary();
            return new Unary(operator, right);
        }
        
        return primary();
    }

    private Expr primary() {
        if (match(TokenType.FALSE)) {
            return new Literal(false);
        }
        if (match(TokenType.TRUE)) {
            return new Literal(true);
        }
        if (match(TokenType.NIL)) {
            return new Literal(null);
        }
        if (match(TokenType.NUMBER, TokenType.STRING)) {
            return new Literal(previous().literal);
        }
        if (match(TokenType.FALSE)) {
            return new Literal(false);
        }

        if (match(TokenType.LEFT_PAREN)) {
            Expr expr = expr();
            consume(TokenType.RIGHT_PAREN);
            return new Grouping(expr);
        }

        return null;
    }

    private Token consume(TokenType tokenType) {
        if (check(tokenType)) {
            return advanceToken();
        }

        return null;
    }


    private boolean match(TokenType... tokenTypes) {
        for (TokenType tokenType : tokenTypes) {
            if (check(tokenType)) {
                advanceToken();
                return true;
            }
        }

        return false;
    }

    private Token advanceToken() {
        if (!isAtEnd()) {
            current++;
        }
        return previous();
    }

    private boolean isAtEnd() {
        return peek().tokenType == TokenType.EOF;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }

    private boolean check(TokenType tokenType) {
        if (isAtEnd()) {
            return false;
        }

        return peek().tokenType == tokenType;
    }


    public Expr parse() {
        return expr();
    }
}
