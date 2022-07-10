package com.leetylarry.theo.expressions;

public interface ExpressionVisitor<T> {
    T visitBinaryExpression(Binary expression);
    T visitGroupingExpression(Grouping expression);
    T visitLiteralExpression(Literal expression);
    T visitUnaryExpression(Unary expression);

    T visitVariableExpr(Variable expression);
}
