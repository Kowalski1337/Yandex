package expression;

/**
 * Created by vadim on 09.04.2017.
 */
public class OpFloat implements Types<Float> {
    public Float parse(String a) {
        return Float.parseFloat(a);
    }
    public Float add(Float a, Float b) throws Exception {
        return a + b;
    }
    public Float subtract(Float a, Float b) throws Exception {
        return a - b;
    }
    public Float multiply(Float a, Float b) throws Exception {
        return a * b;
    }
    public Float divide(Float a, Float b) throws Exception {
        return a / b;
    }
    public Float negate(Float a) throws Exception {
        return -a;
    }
    public Float abs(Float a) throws Exception {
        return a >= 0 ? a : -a;
    }
    public Float square(Float a) throws Exception {
        return a*a;
    }
    public Float mod(Float a, Float b) throws Exception{
        return a % b;
    }
}