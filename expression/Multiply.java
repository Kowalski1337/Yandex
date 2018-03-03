package expression;

import expression.*;

/**
 * Created by vadim on 03.04.2017.
 */
public class Multiply<T> extends BinaryOperator<T> {
    public Multiply(TripleExpression first, TripleExpression second, Types<T> op) {
        super(first, second, op);
    }


    protected T apply(T a, T b) throws Exception {
        return op.multiply(a,b);
    }
}
