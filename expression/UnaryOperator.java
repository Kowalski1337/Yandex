package expression;

/**
 * Created by vadim on 17.04.2017.
 */
abstract public class UnaryOperator<T> implements TripleExpression<T> {
    TripleExpression<T> val;
    Types<T> op;
    UnaryOperator(TripleExpression<T> value, Types<T> op){
        this.op = op;
        val = value;
    }

    abstract protected T apply(T a) throws Exception;

    public T evaluate(T x, T y, T z) throws Exception {
        return apply(val.evaluate(x, y, z));
    }

}
