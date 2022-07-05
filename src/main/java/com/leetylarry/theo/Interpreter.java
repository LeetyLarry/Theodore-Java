package com.leetylarry.theo;

import com.leetylarry.theo.expressions.*;

public class Interpreter implements Visitor<Object> {

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

    public void interpret(Expr expression) {
        Object value = evaluate(expression);
        System.out.println(value);
    }
}
