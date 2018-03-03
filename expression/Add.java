package expression;

/**
 * Created by vadim on 03.04.2017.
 */
public class Add<T> extends BinaryOperator<T> {
    public Add(TripleExpression first, TripleExpression second, Types<T> op) {
        super(first, second, op);
    }

    protected T apply(T a, T b) throws Exception {
        return op.add(a,b);
    }
}
