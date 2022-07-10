package com.leetylarry.theo.statements;

import com.leetylarry.theo.expressions.Expr;

public class Expression extends Statement {
    public Expression(Expr expression) {
        this.expression = expression;
    }

    @Override
    public <R> R accept(StatementVisitor<R> statementVisitor) {
        return statementVisitor.visitExpressionStmt(this);
    }

    public Expr expression;
}