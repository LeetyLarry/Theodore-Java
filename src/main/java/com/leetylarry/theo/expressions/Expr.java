package com.leetylarry.theo.expressions;

public abstract class Expr {

    abstract <T> T accept(Visitor<T> visitor);

}
