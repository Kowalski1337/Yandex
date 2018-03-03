package expression;

/**
 * Created by vadim on 09.04.2017.
 */
public class OpInteger implements Types<Integer> {
    private boolean isCheck;

    public OpInteger(boolean isCheck){
        this.isCheck = isCheck;
    }
    private void checkAdd(Integer a, Integer b) throws Exception {
        if (isCheck && (a > 0 && b > Integer.MAX_VALUE - a || a < 0 &&  b < Integer.MIN_VALUE - a)) {
            throw new OverflowException();
        }
    }

    private void checkSubtract(Integer a, Integer b) throws Exception {
        if (isCheck && (b > 0 && a < Integer.MIN_VALUE + b || b < 0 && a > Integer.MAX_VALUE + b)) {
            throw new OverflowException();
        }
    }

    private void checkDivide(Integer a, Integer b) throws Exception {
        if (isCheck && a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException();
        }
        if (b == 0) {
            throw new MyExceptions("division by zero");
        }
    }

    private void checkNegate(Integer a) throws Exception {
        if (isCheck && a <= Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

    private void checkMultiply(Integer a, Integer b) throws Exception {
        if (isCheck && (a < 0 && b < 0 && a < Integer.MAX_VALUE/b ||
                a < 0 && b > 0 && a < Integer.MIN_VALUE/b ||
                a > 0 && b > 0 && a > Integer.MAX_VALUE/b ||
                a > 0 && b < 0 && b < Integer.MIN_VALUE/a)){
            throw new OverflowException();
        }
    }

    private void checkSqrt(Integer a) throws Exception{
        if (a < 0) {
            throw new MyExceptions("sqrt from negative number");
        }
    }

    public Integer parse(String a) throws Exception {
        return Integer.parseInt(a);
    }

    public Integer add(Integer a, Integer b) throws Exception {
        checkAdd(a, b);
        return a + b;
    }
    public Integer subtract(Integer a, Integer b) throws Exception {
        checkSubtract(a, b);
        return a - b;
    }
    public Integer multiply(Integer a, Integer b) throws Exception {
        checkMultiply(a, b);
        return a * b;
    }
    public Integer divide(Integer a, Integer b) throws Exception {
        checkDivide(a,b);
        return a / b;
    }
    public Integer negate(Integer a) throws Exception {
        checkNegate(a);
        return -a;
    }
    public Integer abs(Integer a) throws Exception {
        if (a < 0) return negate(a);
        return a;
    }

    public Integer square(Integer a) throws Exception {
        return multiply(a,a);
    }

    public Integer mod(Integer a, Integer b) throws Exception{
        return a % b;
    }
}
