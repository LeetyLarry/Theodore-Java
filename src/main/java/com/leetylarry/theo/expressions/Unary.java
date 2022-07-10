package com.leetylarry.theo.expressions;

import com.leetylarry.theo.Token;

public class Unary extends Expr {
    public final Token operator;
    public final Expr right;

    public Unary(Token operator, Expr right) {
        this.operator = operator;
        this.right = right;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
        return expressionVisitor.visitUnaryExpression(this);
    }
}
