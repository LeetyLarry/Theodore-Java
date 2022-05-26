package com.leetylarry.theo.expressions;

public class Grouping extends Expr {
    final Expr expr;

    public Grouping(Expr expr) {
        this.expr = expr;
    }


    @Override
    <T> T accept(Visitor<T> visitor) {
        return visitor.visitGroupingExpression(this);
    }
}
