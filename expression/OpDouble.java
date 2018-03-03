package expression;

/**
 * Created by vadim on 09.04.2017.
 */
public class OpDouble implements Types<Double> {
    public Double parse(String a) {
        return Double.parseDouble(a);
    }

    public Double add(Double a, Double b) throws Exception {
        return a + b;
    }

    public Double subtract(Double a, Double b) throws Exception {
        return a - b;
    }

    public Double multiply(Double a, Double b) throws Exception {
        return a * b;
    }

    public Double divide(Double a, Double b) throws Exception {
        return a / b;
    }

    public Double negate(Double a) throws Exception {
        return -a;
    }

    public Double abs(Double a) throws Exception {
        if (a < 0) return negate(a);
        return a;
    }

    public Double square(Double a) throws Exception {
        return a * a;
    }

    public Double mod(Double a, Double b) throws Exception {
        return a % b;
    }
}