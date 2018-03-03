package expression;

import expression.MyExceptions;
import expression.OverflowException;
import expression.TripleExpression;
import expression.Types;

/**
 * Created by vadim on 03.04.2017.
 */
public class Negate<T> extends UnaryOperator<T> {

    public Negate(TripleExpression<T> val, Types<T> op) {
        super(val, op);
    }

    protected T apply(T a) throws Exception {
        return op.negate(a);
    }
}