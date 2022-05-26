package com.leetylarry.theo.expressions;

public class Literal extends Expr {
    final Object value;

    public Literal(Object value) {
        this.value = value;
    }


    @Override
    <T> T accept(Visitor<T> visitor) {
        return visitor.visitLiteralExpression(this);
    }
}
