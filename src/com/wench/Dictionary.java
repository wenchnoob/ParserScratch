package com.wench;

import java.util.HashMap;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Dictionary {

    private static final String blanks = "\\p{Blank}*";
    private static final  String space = "\\p{Space}";
    private static final String whitespace = "\\s*";

    public static final
    String NUMBER = "%1$s[0-9]+(|\\.?[0-9]+)%1$s".formatted(blanks),
            VARIABLE = "%1$s[a-zA-Z]+\\p{Blank}%1$s".formatted(blanks),
            ARITHMETHICOPERATORS = "%1$s\\+|-|\\*|\\*{2}|/|/{2}%1$s".formatted(blanks),
            SUBEXPRESSION = "%1$s\\([^\\)]+\\)%1$s".formatted(blanks),
            ASSIGNMENT = "%1$s%2$s=%2$s%1$s".formatted(blanks, space),
            EQUALITY = "%1$s%2$s==%2$s%1$s".formatted(blanks, space),
            FLOOR = "%1$sfloor".formatted(blanks),
            CEIL = "%1$sceil".formatted(blanks),
            BLOCK = ".*%1$s\\{(%1$s.*%1$s)*\\}%1$s".formatted(whitespace);

    public static final String LANGUAGE = String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s",
            BLOCK,
            NUMBER,
            VARIABLE,
            ARITHMETHICOPERATORS,
            SUBEXPRESSION,
            ASSIGNMENT,
            EQUALITY,
            CEIL,
            FLOOR
           );


    public static final HashMap<String, BinaryOperator<Double>> BINARYOPERATIONS = new HashMap<>() {
        {
            put("+", (arg1, arg2) -> arg1 + arg2);
            put("-", (arg1, arg2) -> arg1 - arg2);
            put("*", (arg1, arg2) -> arg1 * arg2);
            put("**", (arg1, arg2) -> Math.pow(arg1, arg2));
            put("/", (arg1, arg2) -> Double.valueOf(arg1) / arg2);
            put("//", (arg1, arg2) -> Math.floor(arg1 / arg2));
        }
    };

    public static final HashMap<String, UnaryOperator<Double>> UNARYOPERATIONS = new HashMap<>() {
        {
            put("++", arg1 -> arg1);
            put("--", arg1 -> arg1);
            put("-", arg1 -> -arg1);
            put("floor", arg1 -> Math.floor(arg1));
            put("ceil", arg1 -> Math.ceil(arg1));

        }
    };

}
