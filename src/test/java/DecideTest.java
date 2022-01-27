import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DecideTest {

    private Parameters parameters;
    private double[][] points;
    private Decide decide;

    private double[][] points2;
    private Decide decide2;

    public DecideTest() {
        parameters = new Parameters();
        points = new double[][]{{1.0D, 1.0D}, {100.0D, 100.0D}};
        int numpoints = points.length;
        decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        points2 = new double[][]{{1.0D, 1.0D}, {1.1, 1.1}};
        int numpoints2 = points2.length;
        decide2 = new Decide(numpoints2, points2, parameters, (LCM)null, (boolean[])null);
    }

    @Test
    public void LIC0Pass() {
        boolean LIC0True = decide.LIC0();
        assertTrue(LIC0True);
    }

    @Test
    public void LIC0Fail() {
        boolean LIC0False = decide2.LIC0();
        assertFalse(LIC0False);
    }
}
