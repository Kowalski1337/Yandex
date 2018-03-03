package expression;

import expression.generic.GenericTabulator;
import expression.parser.ExpressionParser;

/**
 * Created by vadim on 03.04.2017.
 */
public class TryHard {
    public static void main(String[] args) throws Exception {
        GenericTabulator lol = new GenericTabulator();

        int x1 = 1, x2 = 1, y1 = 1, y2 = 3, z1 = -10, z2 = -8;

        Object[][][] res = lol.tabulate("d", "234. + x", x1, x2, y1, y2, z1, z2);
        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int k = 0; k <= z2 - z1; k++) {
                    System.out.print(res[i][j][k] + " ");
                }
                System.out.print("\n");
            }
            System.out.print("\n\n");
        }
    }
}

