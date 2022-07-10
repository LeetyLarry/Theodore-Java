package com.leetylarry.theo.expressions;

import com.leetylarry.theo.Token;

public class Variable extends Expr {
    public Variable(Token name) {
        this.name = name;
    }

    @Override
    public <R> R accept(ExpressionVisitor<R> visitor) {
        return visitor.visitVariableExpr(this);
    }

    public Token name;
}