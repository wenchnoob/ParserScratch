package com.wench;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    private final String expression;
    private Matcher matcher;

    public Lexer(String expression) {
        this.expression = expression;
        matcher = Pattern.compile(Dictionary.LANGUAGE).matcher(expression);
    }

    public Token nextToken() {
        boolean tokenFound = matcher.find();
        if (tokenFound) {
            String match = matcher.group();
            return new Token<> (match, typeOf(match));
        }
        return null;
    }

    public List<Token> tokens() {
        List<Token> tokens = new ArrayList<>();
        Token tok;
        while((tok = nextToken()) != null) tokens.add(tok);
        return tokens;
    }

    private Token.Type typeOf(String match) {
        if (Pattern.compile(Dictionary.NUMBER).matcher(match).matches()) return Token.Type.NUMBER;
        if (Pattern.compile(Dictionary.ARITHMETHICOPERATORS).matcher(match).matches()) return Token.Type.BINARYOPERATOR;
        if (Pattern.compile(Dictionary.SUBEXPRESSION).matcher(match).matches()) return Token.Type.SUBEXPRESSION;
        return Token.Type.UNKNOWN;
    }

    public String getExpression() { return this.expression; }
}
