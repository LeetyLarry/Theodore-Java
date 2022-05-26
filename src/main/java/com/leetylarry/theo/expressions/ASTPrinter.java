package com.leetylarry.theo.expressions;

public class ASTPrinter implements Visitor<String>{
    @Override
    public String visitBinaryExpression(Binary expr) {
        return parenthesize(expr.operator.getLexeme(),
                expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpression(Grouping expr) {
        return parenthesize("group", expr.expr);
    }

    @Override
    public String visitLiteralExpression(Literal expr) {
        if (expr.value == null) {
            return "nil";
        }

        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpression(Unary expr) {
        return parenthesize(expr.operator.getLexeme(), expr.right);
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();
        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");
        return builder.toString();
    }
}
