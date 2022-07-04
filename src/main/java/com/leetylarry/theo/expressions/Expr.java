package com.leetylarry.theo.expressions;

public abstract class Expr {

    public abstract <T> T accept(Visitor<T> visitor);

}
