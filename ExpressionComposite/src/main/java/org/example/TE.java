package org.example;

import java.util.Stack;
interface Expression {
    int evaluate();
}

class NumericExpression implements Expression{

    int value;

    NumericExpression(int i){
        this.value = i;
    }

    @Override
    public int evaluate() {
        return this.value;
    }
}

abstract class BinaryExpression implements Expression{
    protected final Expression leftExp;
    protected final Expression rightExp;

    BinaryExpression(Expression left, Expression right){
        leftExp = left;
        rightExp = right;
    }
}

class AdditionExpression extends BinaryExpression{

    AdditionExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate() {
        return leftExp.evaluate() + rightExp.evaluate();
    }
}

class SubstractionExpression extends BinaryExpression{

    SubstractionExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate() {
        return leftExp.evaluate() - rightExp.evaluate();
    }
}

class MultiplicationExpression extends BinaryExpression{

    MultiplicationExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate() {
        return leftExp.evaluate() * rightExp.evaluate();
    }
}

class DivisionExpression extends BinaryExpression{

    DivisionExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate() {
        return leftExp.evaluate() / rightExp.evaluate();
    }
}

class TE
{
    private static Expression parse(String s) {
        Stack<Expression> stack = new Stack<>();

        String[] tokens = s.split("\\s+");

        for (String token : tokens) {
            Expression current = null;
            if (token.matches("-?\\d+")) {
                int i = Integer.parseInt(token);
                current = new NumericExpression(i);
            }
            else {
                Expression b = stack.pop();
                Expression a = stack.pop();
                switch (token.charAt(0)) {
                    case '+':
                        current = new AdditionExpression(a,b);
                        break;
                    case '-':
                        current = new SubstractionExpression(a,b);
                        break;
                    case 'x':
                        current = new MultiplicationExpression(a,b);
                        break;
                    case '/':
                        current = new DivisionExpression(a,b);
                        break;
                }
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
