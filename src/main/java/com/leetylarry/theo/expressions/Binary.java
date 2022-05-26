package com.leetylarry.theo.expressions;

import com.leetylarry.theo.Token;

public class Binary extends Expr {

    final Expr left;
    final Token operator;
    final Expr right;

    public Binary(Expr left, Token operator, Expr right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    <T> T accept(Visitor<T> visitor) {
        return visitor.visitBinaryExpression(this);
    }
}
