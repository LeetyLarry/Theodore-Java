package com.leetylarry.theo.statements;

import com.leetylarry.theo.expressions.Expr;

public class Print extends Statement {
    public Print(Expr expression) {
        this.expression = expression;
    }

    @Override
    public <R> R accept(StatementVisitor<R> statementVisitor) {
        return statementVisitor.visitPrintStmt(this);
    }

    public Expr expression;
}