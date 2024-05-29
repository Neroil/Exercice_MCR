package org.example;

import java.util.Stack;

class TE
{
    static abstract class Expression{
        public abstract int evaluate();
    }

    static class NumberExpression extends Expression {
        private final int number;

        public NumberExpression(int i){
            number = i;
        }

        @Override
        public int evaluate() {
            return number;
        }
    }

    static class CompositeExpression extends Expression{

        private final char op;
        private final Expression leftOp;
        private final Expression rightOp;

        public CompositeExpression(char op, Expression leftOp, Expression rightOp){
            this.op = op;
            this.leftOp = leftOp;
            this.rightOp = rightOp;
        }
        @Override
        public int evaluate() {
            return switch(op){
                case('+') -> (leftOp.evaluate() + rightOp.evaluate());
                case('-') -> (leftOp.evaluate() - rightOp.evaluate());
                case('x') -> (leftOp.evaluate() * rightOp.evaluate());
                case('/') -> (leftOp.evaluate() / rightOp.evaluate());
                default -> throw new IllegalStateException("Unexpected value: " + op);
            };
        }
    }

    private static Expression parse(String s) {
        Stack<Expression> stack = new Stack<>();

        String[] tokens = s.split("\\s+");

        for (String token : tokens) {
            Expression current = null;
            if (token.matches("-?\\d+")) {
                int i = Integer.parseInt(token);
                current = new NumberExpression(i);
            }
            else {
                Expression b = stack.pop();
                Expression a = stack.pop();
//                switch (token.charAt(0)) {
//                    case '+':
//                        current = new CompositeExpression('+', a, b);
//                        break;
//                    case '-':
//                        current = new CompositeExpression('-', a, b);
//                        break;
//                    case 'x':
//                        current = new CompositeExpression('x', a, b);
//                        break;
//                    case '/':
//                        current = new CompositeExpression('/', a, b);
//                        break;
//                }
                current = new CompositeExpression(token.charAt(0), a, b);
            }
            stack.push(current);
        }
        return stack.pop();
    }

    private static void test(String input, int result) {
        int val = parse(input).evaluate();
        System.out.printf("%s -> %d : ys%b\n",
                input, val, val == result);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            test("0", 0);
            test("1 2 +", 3);
            test("-4 5 +", 1);
            test("5 2 /", 2);
            test("5 1 2 + 4 x 3 - +", 14);
            test("10 2 + 3 2 x /", 2);
            test("4 2 5 x + 1 3 2 x + /", 2);
        }
        else {
            String s = String.join(" ", args);
            Expression expression = parse(s);

            System.out.println(expression);
            System.out.println("Result: " + expression.evaluate());
        }
    }
}