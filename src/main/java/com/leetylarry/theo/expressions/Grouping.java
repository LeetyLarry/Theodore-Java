package com.leetylarry.theo.expressions;

public class Grouping extends Expr {
    public final Expr expr;

    public Grouping(Expr expr) {
        this.expr = expr;
    }


    @Override
    public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
        return expressionVisitor.visitGroupingExpression(this);
    }
}
