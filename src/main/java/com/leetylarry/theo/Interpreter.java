package com.leetylarry.theo;

import com.leetylarry.theo.expressions.*;
import com.leetylarry.theo.statements.*;

import java.util.List;

public class Interpreter implements ExpressionVisitor<Object>, StatementVisitor<Object> {

    private Environment environment = new Environment();

    @Override
    public Object visitBinaryExpression(Binary expression) {
        Object left = evaluate(expression.left);
        Object right = evaluate(expression.right);

        switch (expression.operator.tokenType) {
            case PLUS -> {
                return (double)left + (double)right;
            }
            case MINUS -> {
                return (double)left - (double)right;
            }
            case SLASH -> {
                return (double)left / (double)right;
            }
            case STAR -> {
                return (double)left * (double)right;
            }
            case GREATER_THAN -> {
                return (double)left > (double)right;
            }
            case GREATER_THAN_EQUAL -> {
                return (double)left >= (double)right;
            }
            case LESS_THAN -> {
                return (double)left < (double)right;
            }
            case LESS_THAN_EQUAL -> {
                return (double)left <= (double)right;
            }
            case EXCLAMATION_EQUAL-> {
                return !isEqual(left, right);
            } 
            case DOUBLE_EQUAL -> {
                return isEqual(left, right);
            }
        }
        return null;
    }

    private boolean isEqual(Object object1, Object object2) {
        if (object1 == null && object2 == null) {
            return true;
        }
        if (object1 == null) {
            return false;
        }

        return object1.equals(object2);
    }

    @Override
    public Object visitGroupingExpression(Grouping expression) {
        return evaluate(expression.expr);
    }

    @Override
    public Object visitLiteralExpression(Literal expression) {
        return expression.value;
    }

    @Override
    public Object visitUnaryExpression(Unary expression) {
        Object right = evaluate(expression.right);

        switch (expression.operator.tokenType) {
            case MINUS -> {
                return -(double)right;
            }
            case EXCLAMATION -> {
                return !isTruthy(right);
            }
        }
        return null;
    }

    @Override
    public Object visitVariableExpr(Variable expression) {
        return environment.get(expression.name);
    }

    private boolean isTruthy(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof Boolean) {
            return (boolean) object;
        }

        return true;
    }

    private Object evaluate(Expr expr) {
        return expr.accept(this);
    }

    public void interpret(List<Statement> statements) {
        for (Statement statement : statements) {
            execute(statement);
        }
    }

    private void execute(Statement statement) {
        statement.accept(this);
    }

    @Override
    public Object visitExpressionStmt(Expression statement) {
        evaluate(statement.expression);
        return null;
    }

    @Override
    public Object visitPrintStmt(Print statement) {
        Object value = evaluate(statement.expression);
        System.out.println(stringify(value));
        return null;
    }

    @Override
    public Void visitVarStmt(Var statement) {
        Object value = null;
        if (statement.initializer != null) {
            value = evaluate(statement.initializer);
        }
        environment.define(statement.name.lexeme, value);

        return null;
    }

    @Override
    public Object visitAssignExpr(Assign expr) {
        Object value = evaluate(expr.value);

        return value;
    }

    private String stringify(Object object) {
        if (object == null) return "nil";

        if (object instanceof Double) {
            String text = object.toString();
            if (text.endsWith(".0")) {
                text = text.substring(0, text.length() - 2);
            }
            return text;
        }
        return object.toString();
    }
}
