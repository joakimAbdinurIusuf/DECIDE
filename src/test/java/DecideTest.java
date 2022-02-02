import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    /**
     * Check that LIC1 returns true if there exists at least one set of three consecutive data points
     * that cannot all be contained within or on a circle of radius RADIUS1.
     */
    @Test
    public void LIC1PositiveCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {-10.0, -10.0}, {10.0, 10.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC1True = decide.LIC1();
        assertTrue(LIC1True);
    }

    /**
     * Check that LIC1 returns false if there does not exist at least one set of three consecutive data points
     * that cannot all be contained within or on a circle of radius RADIUS1.
     */
    @Test
    public void LIC1NegativeCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {0.0, 1.0}, {1.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC1False = decide.LIC1();
        assertFalse(LIC1False);
    }

    @Test
    public void LIC1Exception () {
        double[][] points = new double[][]{{1.0D, 1.0D}, {100.0, 100.0}};

        int numpoints = points.length;
        Parameters parameters = new Parameters(0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC1();
        });

        String expectedMessage = "RADIUS1 cannot be negative.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // LIC2

    /**
     *
     * @throws IllegalParameterObjectException
     */
    @Test
    public void LIC2PositiveCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 1.0}, {1.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC2True = decide.LIC2();
        assertTrue(LIC2True);
    }

    @Test
    public void LIC2NegativeCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 1.0}, {1.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0.9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC2False = decide.LIC2();
        assertFalse(LIC2False);
    }

    /**
     * Throws exception when epsilon is less than 0 or greater than pi.
     */
    @ParameterizedTest
    @ValueSource(doubles={-1, 5})
    public void LIC2Exception(double epsilon) {
        double[][] points = new double[][]{{1.0D, 1.0D}, {100.0, 100.0}};

        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, epsilon, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC2();
        });

        String expectedMessage = "Epsilon must be greater than 0 and less than pi.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

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

    /**
     * Test if LIC7 returns true when there exists one set of two data points separated by exactly
     * K PTS consecutive intervening points that are a distance greater than the length
     */
    @Test
    public void LIC7PositiveCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {10.0, 10.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC7True = decide.LIC7();
        assertTrue(LIC7True);
    }

    /**
     * Test if LIC7 returns false when numpoints < 3
     */
    @Test
    public void LIC7NegativeCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {2.0, 2.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC7False = decide.LIC7();
        assertFalse(LIC7False);
    }

    /**
     * Test if LIC7 returns false when there does not exist one set of two data points separated by exactly
     * K PTS consecutive intervening points that are a distance greater than the length
     */
    @Test
    public void LIC7NegativeCase2() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC7False = decide.LIC7();
        assertFalse(LIC7False);
    }

    /**
     * Test if LIC7 throws exception when K_PTS < 1.
     */
    @Test
    public void LIC7Exception1() {
        double[][] points = new double[][]{{1.0, 1.0}, {1.0, 1.0}, {100.0, 100.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC7();
        });

        String expectedMessage = "K_PTS must be greater or equal to 1.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test if LIC7 throws exception when K_PTS > numpoints - 2.
     */
    @Test
    public void LIC7Exception2() {
        double[][] points = new double[][]{{1.0, 1.0}, {1.0, 1.0}, {100.0, 100.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, numpoints, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC7();
        });

        String expectedMessage = "K_PTS must be less than or equal to numpoints - 2.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // LIC8

    // LIC9

    /**
     * Tests if LIC9 returns true if there exists at least one set of three data points separated by exactly
     * C_PTS and D_PTS consecutive intervening points, respectively, that form an angle such that
     * angle < (pi - epsilon) or angle > (pi + epsilon)
     * @throws IllegalParameterObjectException
     */
    @Test
    public void LIC9PositiveCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{3.0, 4.0}, {1.0, 1.0}, {0.0, 0.0}, {1.0, 0.0}, {1.0, 0.0}, {1.0, 0.0}, {1.0, 3.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0.1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC9True = decide.LIC9();
        assertTrue(LIC9True);
    }

    /**
     * Tests if LIC9 returns true if there doesn't exists at least one set of three data points separated by exactly
     * C_PTS and D_PTS consecutive intervening points, respectively, that form an angle such that
     * angle < (pi - epsilon) or angle > (pi + epsilon)
     * @throws IllegalParameterObjectException
     */
    @Test
    public void LIC9NegativeCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{5.0, 5.0}, {1.0, 1.0}, {2.0, 2.0}, {1.0, 0.0}, {1.0, 0.0}, {1.0, 0.0}, {3.0, 5.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC9False = decide.LIC9();
        assertFalse(LIC9False);
    }

    /**
     * Test if LIC9 throws exception when C_PTS is < 1.
     */
    @Test
    public void LIC9Exception1() {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC9();
        });

        String expectedMessage = "C_PTS must be greater or equal to 1.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test if LIC9 throws exception when D_PTS is < 1.
     */
    @Test
    public void LIC9Exception2() {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC9();
        });

        String expectedMessage = "D_PTS must be greater or equal to 1.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test if LIC9 throws exception when C_PTS + D_PTS > numpoints - 3.
     */
    @Test
    public void LIC9Exception3() {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC9();
        });

        String expectedMessage = "The sum of C_PTS and D_PTS must be less than or equal to numpoints - 3.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // LIC10

    /**
     * Check that LIC10 returns true if there exists a set of three data points separated by E_PTS and F_PTS consecutive
     * intervening points that are vertices of a triangle with an area greater than AREA1.
     */
    @Test
    public void LIC10PositiveCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {-100.0, 0.0}, {0.0, 0.0}, {-100, -100}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC10True = decide.LIC10();
        assertTrue(LIC10True);
    }

    /**
     * Check that LIC10 returns false if there does not exist a set of three data points separated by E_PTS and F_PTS consecutive
     * intervening points that are vertices of a triangle with an area greater than AREA1.
     */
    @Test
    public void LIC10NegativeCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {-10.0, 0.0}, {0.0, 0.0}, {-10, -10}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC10False = decide.LIC10();
        assertFalse(LIC10False);
    }

    /**
     * Test if LIC10 throws exception when E_PTS is < 1.
     */
    @Test
    public void LIC10Exception1() {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC10();
        });

        String expectedMessage = "E_PTS must be larger than or equal to 1.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test if LIC10 throws exception when F_PTS is < 1.
     */
    @Test
    public void LIC10Exception2() {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC10();
        });

        String expectedMessage = "F_PTS must be larger than or equal to 1.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test if LIC10 throws exception when E_PTS + F_PTS > numpoints - 3.
     */
    @Test
    public void LIC10Exception3() {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC10();
        });

        String expectedMessage = "The sum of E_PTS and F_PTS must be less than or equal to numpoints - 3.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    // LIC11

    /**
     * Checks so that LIC11 returns false if NUMPOINTS < 3
     */
    @Test
    public void LIC11NegativeCase1() throws IllegalParameterObjectException {
        int numpoints = 2;
        Decide d = new Decide(numpoints, null, null, null, null);
        assertFalse(d.LIC11());
    }

    /**
     * Checks so that LIC11 returns true when two data points exist that are separated by G_PTS consecutive 
     * points, such that X[j] - X[i] < 0 (i<j)
     */
    @Test
    public void LIC11PositiveCase1() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {-1.0, 0.0}, {-2.0, 0.0}, {-3.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0);
        Decide d = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        assertTrue(d.LIC11());
    }

    /**
     * Checks so that LIC11 returns false when there exists no two data points that are separated by G_PTS consecutive 
     * points, such that X[j] - X[i] < 0 (i<j)
     */
    @Test
    public void LIC11NegativeCase2() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{-3.0, 0.0}, {-2.0, 0.0}, {-1.0, 0.0}, {0.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0);
        Decide d = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        assertFalse(d.LIC11());
    }

    /**
     * Checks so that LIC11 throws an exception if G_PTS > NUMPOINTS - 2
     */
    @Test
    public void LIC11Exception1() {
        double[][] points = new double[][]{{0.0, 0.0}, {-1.0, 0.0}, {-2.0, 0.0}, {-3.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0);
        Decide d = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            d.LIC11();
        });

        String expectedMessage = "G_PTS should be less than or equal to NUMPOINTS - 2\n";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Checks so that LIC11 throws an exception if G_PTS < 1
     */
    @Test
    public void LIC11Exception2() {
        double[][] points = new double[][]{{0.0, 0.0}, {-1.0, 0.0}, {-2.0, 0.0}, {-3.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0);
        Decide d = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            d.LIC11();
        });

        String expectedMessage = "G_PTS should be greater than 1\n";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // LIC12

    /**
     * Check that LIC12 returns true if there exists two sets of two data points, separated by K_PTS
     * consecutive intervening points, where the distance between the two points are greater than LENGTH1
     * and less than LENGTH2 respectively.
     */
    @Test
    public void LIC12PositiveCase1() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {20.0, 0.0}, {0.0, -5.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(15, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC12True = decide.LIC12();
        assertTrue(LIC12True);
    }

    /**
     * Check that LIC12 returns true if there exists one set of two data points, separated by K_PTS
     * consecutive intervening points, where the distance between the two points are greater than LENGTH1
     * and less than LENGTH2.
     */
    @Test
    public void LIC12PositiveCase2() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {5.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC12True = decide.LIC12();
        assertTrue(LIC12True);
    }

    /**
     * Check that LIC12 returns false if there does not exist any set of two points, separated by K_PTS
     * consecutive intervening points, where the distance between the two points is greater than LENGTH1.
     */
    @Test
    public void LIC12NegativeCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {5.0, 0.0}, {0.1, 0.5}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(100, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC12False = decide.LIC12();
        assertFalse(LIC12False);
    }

    /**
     * Test if LIC12 throws exception when LENGTH2 < 0.
     */
    @Test
    public void LIC12Exception() {
        double[][] points = new double[][]{{0.0, 0.0}, {2.0, 2.0}, {4.0, 0.0}};

        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC12();
        });

        String expectedMessage = "LENGTH2 cannot be negative.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // LIC13

    // LIC14

    /**
     * Check that LIC14 returns true if there exist two sets of three points, separated by E_PTS and
     * F_PTS consecutive intervening points, with areas greater than AREA1 and less than AREA2, respectively.
     */
    @Test
    public void LIC14PositiveCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {-100, -100}, {0.0, 10.0}, {-2.0, 0.0}, {10.0, 0.0}, {0.0, -2.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 5);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC14True = decide.LIC14();
        assertTrue(LIC14True);
    }

    /**
     * Check that LIC14 returns false if there does not exist any set of three points, separated by exactly E_PTS
     * and F_PTS consecutive intervening points, with area greater than AREA1.
     */
    @Test
    public void LIC14NegativeCase() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {-100, -100}, {0.0, 10.0}, {-2.0, 0.0}, {10.0, 0.0}, {0.0, -2.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 5);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC14False = decide.LIC14();
        assertFalse(LIC14False);
    }

    /**
     * Test if LIC14 throws exception when AREA2 < 0.
     */
    @Test
    public void LIC14Exception1() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {2.0, 2.0}, {4.0, 0.0}};

        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.LIC14();
        });

        String expectedMessage = "AREA2 cannot be negative.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests so that given a CMV the PUM function outputs the correct PUM vector.
     */
    @Test
    public void givenCMV_whenPUM_thenCorrectPUM() throws IllegalParameterObjectException {
        boolean[] CMV = new boolean[]{ true, false };
        LCM lcm = new LCM(new Logic[][]{ { Logic.ANDD, Logic.ORR }, { Logic.NOTUSED, Logic.ANDD } });
        Decide d = new Decide(0, new double[][]{{}}, new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), lcm, new boolean[]{});
        assertArrayEquals(new boolean[][]{ { true, true }, { true, false } }, d.PUM(CMV));
    }

    /**
     * Tests so that given a PUV with all false, the FUV is calculated correctly
     */
    @Test 
    public void FUVPUVAllFalseTest() throws IllegalParameterObjectException {
        boolean[] puv = new boolean[]{false, false, false, false};
        Decide d = new Decide(0, new double[][]{{}}, new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), new LCM(null), puv);
        boolean[] actual = d.FUV(new boolean[][]{ {false, false, false, false}, {false, false, false, false}, {false, false, false, false}, {false, false, false, false} });
        boolean[] expected = new boolean[]{true, true, true, true};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests so that given a PUV with all true, the FUV is calculated correctly
     */
    @Test 
    public void FUVPUVAllTrueTest() throws IllegalParameterObjectException {
        boolean[] puv = new boolean[]{true, true, true, true};
        Decide d = new Decide(0, new double[][]{{}}, new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), new LCM(null), puv);
        boolean[] actual = d.FUV(new boolean[][]{ {true, true, true, true}, {false, false, false, false}, {true, false, false, true}, {false, true, true, true} });
        boolean[] expected = new boolean[]{true, false, false, false};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests so that given a PUV with the wrong dimensions, the FUV throws an Exception
     */
    @Test 
    public void FUVPUVWrongDimension() throws IllegalParameterObjectException {
        boolean[] puv = new boolean[]{true};
        Decide d = new Decide(0, new double[][]{{}}, new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), new LCM(null), puv);
        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            d.FUV(new boolean[][]{ {true, true, true, true}, {false, false, false, false}, {true, false, false, true}, {false, true, true, true} });
        });

        String expectedMessage = "PUV has the wrong dimensions.\n";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
  
    /**
     * Tests so that the PUM function throws an exception if it gets arguments with bad dimensions.
     */
    @Test
    public void PUMWrongDimensions() {
        boolean[] CMV = new boolean[]{ true, false };
        LCM lcm = new LCM(new Logic[][]{ { Logic.ANDD, Logic.ORR }});
        Decide d = new Decide(0, new double[][]{{}}, new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), lcm, new boolean[]{});
        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            d.PUM(CMV);
        });

        String expectedMessage = "LCM does not have the right dimensions.\n";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
