package expression;

/**
 * Created by vadim on 10.04.2017.
 */
public class Abs<T> extends UnaryOperator<T> {
    public Abs(TripleExpression<T> val, Types<T> op) {
        super(val, op);
    }

    protected T apply(T a) throws Exception {
        return op.abs(a);
    }
}
