package expression;

import expression.TripleExpression;
import expression.Types;

/**
 * Created by vadim on 03.04.2017.
 */
abstract public class BinaryOperator<T> implements TripleExpression<T> {
    private TripleExpression<T> fOp;
    private TripleExpression<T> sOp;

    Types<T> op;


    BinaryOperator(TripleExpression<T> first, TripleExpression<T> second, Types<T> op) {
        fOp = first;
        sOp = second;
        this.op = op;
    }

    abstract protected T apply(T a, T b) throws Exception;

    public T evaluate(T x, T y, T z) throws Exception {
        return apply(fOp.evaluate(x, y, z), sOp.evaluate(x, y, z));
    }



}
