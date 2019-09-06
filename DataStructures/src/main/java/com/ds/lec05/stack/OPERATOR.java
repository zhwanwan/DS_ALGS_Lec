package com.ds.lec05.stack;

/**
 * @author zhwanwan
 * @create 2019-09-01 10:10
 */
public enum OPERATOR {

    PLUS("+", 0),
    MINUS("-", 0),
    MULTIPLY("*", 1),
    DIVIDE("/", 1);
    String sign;
    int priority;

    OPERATOR(String sign, int priority) {
        this.sign = sign;
        this.priority = priority;
    }

    public static OPERATOR getOperator(String sign) {
        for (OPERATOR operator : OPERATOR.values()) {
            if (operator.sign.equals(sign))
                return operator;
        }
        return null;
    }
}