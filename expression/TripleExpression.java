package expression;

/**
 * Created by vadim on 03.04.2017.
 */
public interface TripleExpression<T> {
    T evaluate(T x, T y, T z) throws Exception;
}