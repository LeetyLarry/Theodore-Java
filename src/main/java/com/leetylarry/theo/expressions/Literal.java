package com.leetylarry.theo.expressions;

public class Literal extends Expr {
    public final Object value;

    public Literal(Object value) {
        this.value = value;
    }


    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitLiteralExpression(this);
    }
}
