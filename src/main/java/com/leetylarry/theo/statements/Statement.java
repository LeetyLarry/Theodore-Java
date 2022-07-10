package com.leetylarry.theo.statements;


public abstract class Statement {

    public abstract <T> T accept(StatementVisitor<T> statementVisitor);
}
