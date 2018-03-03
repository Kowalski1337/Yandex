package expression;

/**
 * Created by vadim on 03.04.2017.
 */
public class Const<T> implements TripleExpression<T> {
    private T value;
    public Const(T val) {
        value = val;
    }

    public T evaluate(T x, T y, T z) {
        return value;
    }
}
