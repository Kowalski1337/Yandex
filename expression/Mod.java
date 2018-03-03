package expression;

import java.math.BigInteger;

/**
 * Created by vadim on 10.04.2017.
 */
public class Mod<T> extends BinaryOperator<T> {
    public Mod(TripleExpression first, TripleExpression second, Types<T> op) {
        super(first, second, op);
    }

    protected T apply(T a, T b) throws Exception {
        return op.mod(a,b);
    }
}
