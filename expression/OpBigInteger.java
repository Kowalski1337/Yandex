package expression;

import java.math.BigInteger;

/**
 * Created by vadim on 09.04.2017.
 */
public class OpBigInteger implements Types<BigInteger> {
    public BigInteger parse(String a) {
        return new BigInteger(a);
    }

    public BigInteger add(BigInteger a, BigInteger b) throws Exception {
        return a.add(b);
    }
    public BigInteger subtract(BigInteger a, BigInteger b) throws Exception {
        return a.subtract(b);
    }
    public BigInteger multiply(BigInteger a, BigInteger b) throws Exception {
        return a.multiply(b);
    }
    public BigInteger divide(BigInteger a, BigInteger b) throws Exception {
        return a.divide(b);
    }
    public BigInteger negate(BigInteger a) throws Exception {
        return a.negate();
    }
    public BigInteger abs(BigInteger a) throws Exception {
        return a.abs();
    }
    public BigInteger square(BigInteger a) throws Exception {
        return a.multiply(a);
    }
    public BigInteger mod(BigInteger a, BigInteger b) throws Exception {
        return a.remainder(b);
    }

}
