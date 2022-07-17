package com.leetylarry.theo;

import javax.management.RuntimeErrorException;
import java.util.HashMap;
import java.util.Map;

public class Environment {
    private final Map<String, Object> values = new HashMap<>();

    public Object get(Token name) {
        if (values.containsKey(name.lexeme)) {
            return values.get(name.lexeme);
        }

        throw new RuntimeException("Error with: " + name.lexeme);
    }

    public void define(String key, Object value) {
        values.put(key, value);
    }

    void assign(Token name, Object value) {
        if (values.containsKey(name.lexeme)) {
            values.put(name.lexeme, value);
            return;
        }

        throw new RuntimeException("Error with: " + name.lexeme);
    }
}
