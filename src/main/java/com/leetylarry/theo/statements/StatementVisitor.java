package com.leetylarry.theo.statements;

public interface StatementVisitor<R> {

    R visitExpressionStmt(Expression statement);
    R visitPrintStmt(Print statement);
    R visitVarStmt(Var statement);

}
