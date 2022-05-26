# Theodore-Java
Java Prototype of my Theodore programming language

### Theodore Grammar

expression -> literal | unary | binary | grouping;

literal -> NUMBER | STRING | "true" | "false" | nil;

grouping -> "(" expression ")";

unary -> ( "-"  | "!" ) expression;

binary -> expression operator expression;

operator -> "==" | "!=" | "<" | "<=" | ">" | ">=" | "+" | "-" | "*" | "/"



