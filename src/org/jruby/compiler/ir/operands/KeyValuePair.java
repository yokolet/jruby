package org.jruby.compiler.ir.operands;

public class KeyValuePair {
    final private Operand key;
    final private Operand value;

    public KeyValuePair(Operand key, Operand value) {
        this.key = key;
        this.value = value;
    }

    public Operand getKey() {
        return key;
    }

    public Operand getValue() {
        return value;
    }
}
