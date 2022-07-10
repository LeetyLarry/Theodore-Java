package com.leetylarry.theo.statements;

import com.leetylarry.theo.Token;
import com.leetylarry.theo.expressions.Expr;

public class Var extends Statement {
    public Token name;
    public Expr initializer;


    public Var(Token name, Expr initializer) {
        this.name = name;
        this.initializer = initializer;
    }

    @Override
    public <R> R accept(StatementVisitor<R> visitor) {
        return visitor.visitVarStmt(this);
    }
}
