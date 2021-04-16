package com.wench;


import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        System.out.println(Dictionary.BLOCK);
        Pattern pat = Pattern.compile(Dictionary.BLOCK);

        boolean match = pat.matcher("""
                public class Main {
                }
                """).matches();
        System.out.println(match);

        match = pat.matcher("""
                public class Main {
                    public static void main(String args[]) {
                    
                    }
                }
                """).matches();
        System.out.println(match);

        match = pat.matcher("""
                public {
                
                }
                
                """).matches();
        System.out.println(match);

        match = pat.matcher("""
                {}
                """).matches();
        System.out.println(match);


        //new Lexer("6*5*5*12").tokens().forEach(System.out::println);

        //AST node = new AST("6*5*5*12");
        //node.print();

        //AST node2 = new AST("    floor(76*1.2)   +(12*10.5*12)/jake+12.56*10.5+13apple123-12+apple-Mike");
        //node2.print();

        /* new Lexer("""
                public class Main {
                                
                    public static void main(String[] args) {
                                
                        new Lexer("6*5*5*12").tokens().forEach(System.out::println);
                                
                        AST node = new AST("6*5*5*12");
                        node.print();
                                
                        AST node2 = new AST("    floor(76*1.2)   +(12*10.5*12)/jake+12.56*10.5+13apple123-12+apple-Mike");
                        node2.print();
                       \s
                        AST node3 = new AST(""\"
                               \s
                                ""\");
                    }
                }
                                
                """).tokens().forEach(System.out::println);*/

        /* Lexer lex = new Lexer("    floor(76*1.2)   +(12*10.5*12)/jake+12.56*10.5+13apple123-12+apple-Mike");
        Token tok = lex.nextToken();
        while (tok != null) {
            System.out.println(tok);
           tok = lex.nextToken();
        }*/
    }
}
