package expression.generic;


import expression.*;
import expression.parser.ExpressionParser;

/**
 * Created by vadim on 09.04.2017.
 */
public class GenericTabulator implements Tabulator {
    private<T> Object[][][] done(Types<T> op, int x1, int x2, int y1, int y2, int z1, int z2, String expression) {
        Object[][][] ret = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        ExpressionParser<T> p = new ExpressionParser<T>(op);
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        ret[i - x1][j - y1][k - z1] = p.parse(expression).evaluate(op.parse(Integer.toString(i)), op.parse(Integer.toString(j)), op.parse(Integer.toString(k)));
                    } catch (Exception e) {
//                        ret[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }
        return ret;
    }
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Types<?> op = getType(mode);

        return done(op, x1, x2, y1, y2, z1, z2, expression);
    }

    private Types<?> getType(String mode) {
        switch (mode) {
            case "i":
                return new OpInteger(true);
            case "d":
                return new OpDouble();
            case "bi":
                return new OpBigInteger();
            case "f":
                return new OpFloat();
            case "b":
                return new OpByte();
            default:
                return new OpInteger(false);

        }

    }
}
