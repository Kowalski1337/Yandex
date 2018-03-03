package expression;

/**
 * Created by vadim on 10.04.2017.
 */
public class Square<T> extends UnaryOperator<T>   {

    public Square(TripleExpression<T> val, Types<T> op) {
        super(val,op);
    }


    protected T apply(T a) throws Exception {
        return op.square(a);
    }
}
