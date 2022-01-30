import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DecideTest {

    // LIC0

    /**
     * Check that LIC0 returns true if there exists two consecutive data points with a distance greater than LENGTH1.
     */
    @Test
    public void LIC0PositiveCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{1.0, 1.0}, {100.0, 100.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC0True = decide.LIC0();
        assertTrue(LIC0True);
    }

    /**
     * Check that LIC0 returns false if there does not exist two consecutive data points
     * with a distance greater than LENGTH1.
     */
    @Test
    public void LIC0NegativeCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{1.0, 1.0}, {1.1, 1.1}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC0False = decide.LIC0();
        assertFalse(LIC0False);
    }

    @Test
    public void LIC0Exception () {
        double[][] points = new double[][]{{1.0D, 1.0D}, {100.0, 100.0}};

        int numpoints = points.length;
        Parameters parameters = new Parameters(-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC0();
        });

        String expectedMessage = "LENGTH1 cannot be negative.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // LIC1

    // LIC2

    // LIC3

    /**
     * Check that LIC3 returns true when the area of three consecutive points is greater than AREA1.
     * @throws IllegalParameterObjectException
     */
    @Test
    public void LIC3PositiveCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{1.0, 3.0}, {-10.0, -1.0}, {8.0, 15.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC3True = decide.LIC3();
        assertTrue(LIC3True);
    }

    /**
     * Check that LIC3 returns false when the area of three consecutive points is equal to AREA1.
     * @throws IllegalParameterObjectException
     */
    @Test
    public void LIC3NegativeCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {10.0, 0.0}, {0.0, 10.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC3False = decide.LIC3();
        assertFalse(LIC3False);
    }

    /**
     * Throw exception when area is less than 0.
     */
    @Test
    public void LIC3Exception() {
        double[][] points = new double[][]{{1.0, 1.0}, {100.0, 100.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC3();
        });

        String expectedMessage = "AREA1 cannot be negative.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // LIC4

    /**
     * Check that LIC4 returns true when there exists a set of Q_PTS consecutive data points
     * that lie in more than QUADS quadrants.
     */
    @Test
    public void LIC4PositiveCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{1.0, 1.0}, {2.0, 2.0}, {-1.0, 1.0}, {-1.0, -1.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC4True = decide.LIC4();
        assertTrue(LIC4True);
    }

    /**
     * Check that LIC4 returns true when there exists a set of Q_PTS consecutive data points
     * that lie in more than QUADS quadrants, and the data points are ambiguous.
     */
    @Test
    public void LIC4PositiveCaseAmbiguous() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 1.0}, {0.0, 2.0}, {-1.0, 0.0}, {0.0, -1.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC4True = decide.LIC4();
        assertTrue(LIC4True);
    }

    /**
     * Check that LIC4 returns false when there does not exist a set of Q_PTS consecutive data points
     * that lie in more than QUADS quadrants.
     */
    @Test
    public void LIC4NegativeCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{-2.0, -5.0}, {-2.0, -3.0}, {-1.0, 1.0}, {-1.0, -1.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC4False = decide.LIC4();
        assertFalse(LIC4False);
    }

    /**
     * Check that LIC4 returns false when there does not exist a set of Q_PTS consecutive data points
     * that lie in more than QUADS quadrants, and the data points are ambiguous.
     */
    @Test
    public void LIC4NegativeCaseAmbiguous() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, -3.0}, {-1.0, 1.0}, {-1.0, -1.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC4False = decide.LIC4();
        assertFalse(LIC4False);
    }

    /**
     * Test if LIC4 throws exception when Q_PTS < 2.
     */
    @Test
    public void LIC4Exception1() {
        double[][] points = new double[][]{{1.0, 1.0}, {100.0, 100.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC4();
        });

        String expectedMessage = "Q_PTS must be greater than or equal to 2.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test if LIC4 throws exception when Q_PTS > numpoints.
     */
    @Test
    public void LIC4Exception2() {
        double[][] points = new double[][]{{1.0, 1.0}, {100.0, 100.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, numpoints + 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC4();
        });

        String expectedMessage = "Q_PTS must be less than than or equal to numpoints.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test if LIC4 throws exception when QUADS < 0.
     */
    @Test
    public void LIC4Exception3() {
        double[][] points = new double[][]{{1.0, 1.0}, {100.0, 100.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, numpoints, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC4();
        });

        String expectedMessage = "QUADS must be greater than or equal to 1.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test if LIC4 throws exception when QUADS > 3.
     */
    @Test
    public void LIC4Exception4() {
        double[][] points = new double[][]{{1.0, 1.0}, {100.0, 100.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, numpoints, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC4();
        });

        String expectedMessage = "QUADS must be less than than or equal to 3.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // LIC5

    /**
     * Check that LIC5 returns true if there exists two consecutive points such that X_i+1 - X_i < 0.
     */
    @Test
    public void LIC5PositiveCase() {
        double[][] points = new double[][]{{10.0, 1.0}, {5.0, 1.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC5True = decide.LIC5();
        assertTrue(LIC5True);
    }

    /**
     * Check that LIC5 returns false if there does not exist two consecutive points such that X_i+1 - X_i < 0.
     */
    @Test
    public void LIC5NegativeCase() {
        double[][] points = new double[][]{{5.0, 1.0}, {10.0, 1.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC5False = decide.LIC5();
        assertFalse(LIC5False);
    }

    // LIC6

    /**
     * Check that LIC6 returns true if there exists a point with a distance greater than DIST to the line
     * joining the first and last points in a set of N_PTS consecutive data points.
     */
    @Test
    public void LIC6PositiveCase()  throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {2.0, 2.0}, {4.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC6True = decide.LIC6();
        assertTrue(LIC6True);
    }

    /**
     * Check that LIC6 returns true if there exists a point with a distance greater than DIST to two
     * coinciding first and last points in a set of N_PTS consecutive data points.
     */
    @Test
    public void LIC6PositiveCase2() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {2.0, 2.0}, {0.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC6True = decide.LIC6();
        assertTrue(LIC6True);
    }

    /**
     * Check that LIC6 returns false if there does not exist any point with a distance greater than DIST to the line
     * joining the first and last points in any set of N_PTS consecutive data points.
     */
    @Test
    public void LIC6NegativeCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {2.0, 2.0}, {4.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 10, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC6False = decide.LIC6();
        assertFalse(LIC6False);
    }

    /**
     * Test if LIC6 throws exception when DIST < 0.
     */
    @Test
    public void LIC6Exception1() {
        double[][] points = new double[][]{{0.0, 0.0}, {2.0, 2.0}, {4.0, 0.0}};

        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, -1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC6();
        });

        String expectedMessage = "DIST cannot be negative.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test if LIC6 throws exception when N_PTS < 3.
     */
    @Test
    public void LIC6Exception2() {
        double[][] points = new double[][]{{0.0, 0.0}, {2.0, 2.0}, {4.0, 0.0}};

        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC6();
        });

        String expectedMessage = "N_PTS cannot be smaller than 3.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test if LIC6 throws exception when N_PTS < NUMPOINTS.
     */
    @Test
    public void LIC6Exception3() {
        double[][] points = new double[][]{{0.0, 0.0}, {2.0, 2.0}, {4.0, 0.0}};

        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 1, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC6();
        });

        String expectedMessage = "N_PTS cannot be larger than number of data points.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // LIC7

    // LIC8

    // LIC9

    // LIC10

    // LIC11

    // LIC12

    // LIC13

    // LIC14

    /**
     * Tests so that given a CMV the PUM function outputs the correct PUM vector.
     */
    @Test
    public void givenCMV_whenPUM_thenCorrectPUM() {
        boolean[] CMV = new boolean[]{ true, false };
        LCM lcm = new LCM(new Logic[][]{ { Logic.ANDD, Logic.ORR }, { Logic.NOTUSED, Logic.ANDD } });
        Decide d = new Decide(0, new double[][]{{}}, new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), lcm, new boolean[]{});
        assertArrayEquals(new boolean[][]{ { true, true }, { true, false } }, d.PUM(CMV));
    }
}
