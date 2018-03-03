package expression;

import expression.BinaryOperator;
import expression.TripleExpression;
import expression.Types;

/**
 * Created by vadim on 03.04.2017.
 */
public class Divide<T> extends BinaryOperator<T> {
    public Divide(TripleExpression first, TripleExpression second, Types<T> op) {
        super(first, second, op);
    }

    protected T apply(T a, T b) throws Exception {
        return op.divide(a,b);
    }
}