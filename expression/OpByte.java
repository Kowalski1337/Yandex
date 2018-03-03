package expression;

/**
 * Created by vadim on 09.04.2017.
 */
public class OpByte implements Types<Byte> {

    private void checkDivide(Byte a, Byte b) throws Exception {
        if (b == 0) {
            throw new MyExceptions("division by zero");
        }
    }

    private void checkSqrt(Byte a) throws Exception{
        if (a < 0) {
            throw new MyExceptions("sqrt from negative number");
        }
    }

    public Byte parse(String a) throws Exception {
        return Byte.parseByte(a);
    }

    private static int converter = 0xFF;

    private byte intToByte(int x){
        Integer y = x & converter;
        return y.byteValue();
    }

    public Byte add(Byte a, Byte b) throws Exception {
        return intToByte(a + b);
    }
    public Byte subtract(Byte a, Byte b) throws Exception {
        return intToByte(a - b);
    }
    public Byte multiply(Byte a, Byte b) throws Exception {
        return intToByte(a * b);
    }
    public Byte divide(Byte a, Byte b) throws Exception {
        return intToByte(a / b);
    }
    public Byte negate(Byte a) throws Exception {
        return intToByte(-a);
    }
    public Byte abs(Byte a) throws Exception {
        if (a < 0) return negate(a);
        return a;
    }
    public Byte square(Byte a) throws Exception {
        return multiply(a,a);
    }
    public Byte mod(Byte a, Byte b) throws Exception{
        return intToByte(a % b);
    }
}
