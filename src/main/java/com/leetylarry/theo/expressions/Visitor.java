package com.leetylarry.theo.expressions;

public interface Visitor<T> {
    T visitBinaryExpression(Binary expression);
    T visitGroupingExpression(Grouping expression);
    T visitLiteralExpression(Literal expression);
    T visitUnaryExpression(Unary expression);
}
