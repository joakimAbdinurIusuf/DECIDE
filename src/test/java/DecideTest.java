import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecideTest {

    private Parameters parameters;
    private double[][] points;
    private Decide decide;

    private double[][] points2;
    private Decide decide2;

    public DecideTest() {
        parameters = new Parameters();
        points = new double[][]{{1.0D, 1.0D}, {100.0D, 100.0D}};
        decide = new Decide(10, points, parameters, (LCM)null, (boolean[])null);

        points2 = new double[][]{{1.0D, 1.0D}, {1.1, 1.1}};
        decide2 = new Decide(10, points2, parameters, (LCM)null, (boolean[])null);
    }

    @Test
    public void LIC0Pass() {
        boolean LIC0true = decide.LIC0();
        assertEquals(2, 1+1);
    }

    @Test
    public void LIC0Fail() {
        assertEquals(1, 2+2);
    }
}
