package com.leetylarry.theo.expressions;

import com.leetylarry.theo.Token;

public class Assign extends Expr {
    public Assign(Token name, Expr value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public <R> R accept(ExpressionVisitor<R> visitor) {
        return visitor.visitAssignExpr(this);
    }

    public Token name;
    public Expr value;

}
