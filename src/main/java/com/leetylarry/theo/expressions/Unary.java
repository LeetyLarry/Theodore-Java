package com.leetylarry.theo.expressions;

import com.leetylarry.theo.Token;

public class Unary extends Expr {
    final Token operator;
    final Expr right;

    public Unary(Token operator, Expr right) {
        this.operator = operator;
        this.right = right;
    }

    @Override
    <T> T accept(Visitor<T> visitor) {
        return visitor.visitUnaryExpression(this);
    }
}
