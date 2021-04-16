package com.wench;

public class AST {
    ASTNode root;
    String expression;

    public AST(String expression) {
        this.expression = expression;
        init();
    }

    public ASTNode getRoot() {
        if (expression.trim().equals("")) root = new ASTNode(new Token(Double.NaN, Token.Type.NUMBER));
        return root;
    }

    private void init() {
        Lexer lex = new Lexer(expression);
       lex.tokens().forEach(token -> insert(token));
    }

    private void insert(Token token) {
        if (token.type() == Token.Type.SUBEXPRESSION) {
            ASTNode head = new ASTNode(new Token<>("()", Token.Type.OPERATOR));
            String value = (String)token.value();
            AST temp = new AST(value.substring(1, value.length()-1));
            head.setRight(temp.getRoot());

            if (root == null) {
                this.root = head;
            } else {
                insertTo(root, head);
            }
        } else {
            if (root == null) {
                root = new ASTNode(token);
            } else {
                insertTo(root, new ASTNode(token));
            }
        }
    }

    private void insertTo(ASTNode insertionPoint, ASTNode toInsert) {
        if (true) {
            if (toInsert.left() == null) {
                toInsert.setLeft(insertionPoint);
                insertionLogic(insertionPoint, toInsert);
            } else if (toInsert.right() == null) {
                toInsert.setRight(insertionPoint);
                insertionLogic(insertionPoint, toInsert);
            } else {
                insertTo(insertionPoint.right(), toInsert);
            }
        } else {
            if (insertionPoint.left() == null) {
                insertionPoint.setLeft(toInsert);
                toInsert.setParent(insertionPoint);
            } else if (insertionPoint.right() == null) {
                insertionPoint.setRight(toInsert);
                toInsert.setParent(insertionPoint);
            } else {
                insertTo(insertionPoint.right(), toInsert);
            }
        }
    }

    private void insertionLogic(ASTNode insertionPoint, ASTNode toInsert) {
        ASTNode oldParent = insertionPoint.parent();
        if (oldParent != null) {
            if (oldParent.right() == insertionPoint) {
                oldParent.setRight(toInsert);
            } else {
                oldParent.setLeft(toInsert);
            }
        } else {
            this.root = toInsert;
            insertionPoint.setParent(toInsert);
        }
    }

    public void  print() {
        printFrom(root);
        System.out.println();
    }

    private void printFrom(ASTNode from) {
        if (from == null) return;
        if(String.valueOf(from.value().value()).equals("()")) System.out.print("(");
        printFrom(from.left());
        if(!String.valueOf(from.value().value()).equals("()")) System.out.print(from);
        printFrom(from.right());
        if(String.valueOf(from.value().value()).equals("()")) System.out.print(")");
    }
}