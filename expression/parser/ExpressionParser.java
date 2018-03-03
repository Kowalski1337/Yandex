package expression.parser;

import expression.*;

import java.util.LinkedList;

/**
 * Created by vadim on 03.04.2017.
 */
public class ExpressionParser<T> implements Parser<T> {
    private int index;
    private String expression;
    private T constant;
    private char variable;
    private boolean wasOp;
    private Types<T> op;

    private enum State {NUMBER, PLUS, UNARYMINUS, BINARYMINUS, MUL, DIV, LBRACKET, RBRACKET, VARIABLE, ABS, SQUARE, MOD}

    private State current;

    public ExpressionParser(Types<T> op) {
        this.op = op;
    }

    private void error(int i, String message) throws Exception {
        StringBuilder st = new StringBuilder();

        for (int j = 0; j < i; j++) {
            st.append(" ");
        }

        st.append("^");

        throw new MyExceptions(message + "\n" + expression + "\n" + st.toString());
    }


    private void miss(boolean can, int dl) throws Exception {
        if (can) {
            wasOp = true;
        } else {
            error(index - dl, "missing prev operand");
        }
    }

    private char getNextChar() {
        if (index < expression.length()) {
            char ret = expression.charAt(index++);
            return ret;
        } else {
            return '!';
        }
    }

    private void parseNumber(char ch, int dl) throws Exception {
        int f = index - dl, l = index - 1;
        while (Character.isDigit(ch) || ch == '.') {
            l++;
            ch = getNextChar();
        }
        index--;
        try {
            constant = op.parse(expression.substring(f, l));
        } catch (NumberFormatException e) {
            throw new MyExceptions("constant overflow " + expression.substring(f, l));
        }
        current = State.NUMBER;
        wasOp = false;
    }


    private void getNext() throws Exception {
        if (index >= expression.length() && wasOp) {
            error(index, "missing next operand");
        }
        while (Character.isWhitespace(getNextChar())) {

        }
        index--;
        char ch = getNextChar();
        if (Character.isDigit(ch)) {
            parseNumber(ch, 1);
        } else {
            switch (ch) {
                case '-':
                    if (!wasOp) {
                        wasOp = true;
                        current = State.BINARYMINUS;
                    } else {
                        char s = expression.charAt(index);
                        if (Character.isDigit(s)) {
                            parseNumber(getNextChar(), 2);
                            current = State.NUMBER;
                            wasOp = false;
                        } else {
                            current = State.UNARYMINUS;
                        }
                    }
                    break;
                case '+':
                    miss(!wasOp, 2);
                    current = State.PLUS;
                    break;
                case '*':
                    miss(!wasOp, 2);
                    current = State.MUL;
                    break;
                case '/':
                    miss(!wasOp, 2);
                    current = State.DIV;
                    break;
                case ')':
                    miss(!wasOp, 2);
                    wasOp = false;
                    current = State.RBRACKET;
                    break;
                case '(':
                    current = State.LBRACKET;
                    break;
                case 'x':
                case 'y':
                case 'z':
                    current = State.VARIABLE;
                    wasOp = false;
                    variable = ch;
                    break;
                case 'l':
                case 'p':
                case 's':
                case 'a':
                case 'm':
                    int f = index - 1;
                    while (Character.isAlphabetic(ch) || Character.isDigit(ch)) {
                        ch = getNextChar();
                    }
                    if (index != expression.length()) {
                        index--;
                    }
                    String s = expression.substring(f, index);
                    switch (s) {
                        case "square":
                            miss(wasOp, 5);
                            current = State.SQUARE;
                            break;
                        case "abs":
                            miss(wasOp, 4);
                            current = State.ABS;
                            break;
                        case "mod":
                            miss(!wasOp, 3);
                            current = State.MOD;
                            break;

                        default:
                            error(f, "Unknown identifier");
                    }
                    break;


                default:
                    if (!Character.isWhitespace(ch))
                        error(index - 1, "Unexpected symbol");
            }
        }
    }


    private TripleExpression<T> unary() throws Exception {
        getNext();
        TripleExpression<T> ret;
        switch (current) {
            case NUMBER:
                ret = new Const<T>(constant);
                getNext();
                break;

            case VARIABLE:
                ret = new Variable<T>(Character.toString(variable));
                getNext();
                break;

            case UNARYMINUS:
                ret = new Negate<T>(unary(), op);
                break;

            case ABS:
                ret = new Abs<T>(unary(), op);
                break;

            case LBRACKET:
                ret = addSubt();
                getNext();
                break;

            case SQUARE:
                ret = new Square<T>(unary(), op);
                break;

            default:
                ret = null;
        }
        return ret;
    }

    private TripleExpression<T> MULDIV() throws Exception {
        TripleExpression<T> left = unary();
        while (true) {
            switch (current) {
                case MUL:
                    left = new Multiply<T>(left, unary(), op);
                    break;

                case DIV:
                    left = new Divide<T>(left, unary(), op);
                    break;

                case MOD:
                    left = new Mod<T>(left, unary(), op);
                    break;

                default:
                    return left;
            }
        }
    }

    private TripleExpression<T> addSubt() throws Exception {
        TripleExpression<T> left = MULDIV();
        while (true) {
            switch (current) {
                case BINARYMINUS:
                    left = new Subtract<T>(left, MULDIV(), op);
                    break;

                case PLUS:
                    left = new Add<T>(left, MULDIV(), op);
                    break;

                default:
                    return left;
            }
        }
    }

    /*private TripleExpression<T> minMax() throws Exception {
        TripleExpression<T> left = addSubt();
        while (true) {
            switch (current) {
                case MIN:
                    left = new CheckedMin(left, addSubt());
                    break;
                case MAX:
                    left = new CheckedMax(left, addSubt());
                    break;
                default:
                    return left;
            }
        }
    }*/

    public TripleExpression<T> parse(String expr) throws Exception {
        expression = expr;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                stack.add(i);
            } else if (expression.charAt(i) == ')') {
                if (stack.size() == 0) {
                    error(i, "Missing open parenthesis");
                }
                stack.removeLast();
            }
        }
        if (stack.size() != 0) {
            int l = stack.peekLast();
            error(l, "Missing close parenthesis");
        }
        index = 0;
        wasOp = true;
        return addSubt();
    }
}
