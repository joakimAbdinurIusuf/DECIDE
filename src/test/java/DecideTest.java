import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DecideTest {

    // LIC0

    @Test
    public void givenDistanceGreaterThanLENGTH1BetweenTwoConsecutivePoints_whenLIC0_thenAssertTrue() {
        double[][] points = new double[][]{{1.0, 1.0}, {100.0, 100.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC0True = decide.LIC0();
        assertTrue(LIC0True);
    }

    @Test
    public void givenDistanceGreaterThanLENGTH1DoesNotExistBetweenTwoConsecutivePoints_whenLIC0_thenAssertFalse() {
        double[][] points = new double[][]{{1.0, 1.0}, {1.1, 1.1}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC0False = decide.LIC0();
        assertFalse(LIC0False);
    }

    @Test
    public void givenLENGTH1IsLessThanZero_whenLIC0_thenExceptionIsThrownAndMessagePrinted() {
        double[][] points = new double[][]{{1.0D, 1.0D}, {100.0, 100.0}};

        int numpoints = points.length;
        Parameters parameters = new Parameters(-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            decide.LIC0();
        });

        String expectedMessage = "LENGTH1 cannot be negative.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // LIC1

    // LIC2

    // LIC3

    @Test
    public void givenAreaOfThreeConsecutivePointsGreaterThanAREA1_whenLIC3_thenAssertTrue() {
        double[][] points = new double[][]{{1.0, 3.0}, {-10.0, -1.0}, {8.0, 15.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC3True = decide.LIC3();
        assertTrue(LIC3True);
    }

    @Test
    public void givenAreaOfThreeConsecutivePointsEqualToAREA1_whenLIC3_thenAssertFalse() {
        double[][] points = new double[][]{{0.0, 0.0}, {10.0, 0.0}, {0.0, 10.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC3False = decide.LIC3();
        assertFalse(LIC3False);
    }

    @Test
    public void givenAREA1IsLessThanZero_whenLIC3_thenExceptionIsThrownAndMessagePrinted() {
        double[][] points = new double[][]{{1.0, 1.0}, {100.0, 100.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
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
    public void LIC4PositiveCase() {
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
    public void LIC4PositiveCaseAmbiguous() {
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
    public void LIC4NegativeCase() {
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
    public void LIC4NegativeCaseAmbiguous() {
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

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
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

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
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

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
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

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            decide.LIC4();
        });

        String expectedMessage = "QUADS must be less than than or equal to 3.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // LIC5

    @Test
    public void givenExistsTwoConsecutivePointsWhereXjMinusXiIsLessThanZero_whenLIC5_thenAssertTrue() {
        double[][] points = new double[][]{{10.0, 1.0}, {5.0, 1.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC5True = decide.LIC5();
        assertTrue(LIC5True);
    }

    @Test
    public void givenDoesNotExistTwoConsecutivePointsWhereXjMinusXiIsLessThanZero_whenLIC5_thenAssertFalse() {
        double[][] points = new double[][]{{5.0, 1.0}, {10.0, 1.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC5False = decide.LIC5();
        assertFalse(LIC5False);
    }

    // LIC6

    // LIC7

    // LIC8

    // LIC9

    // LIC10

    // LIC11

    // LIC12

    // LIC13

    // LIC14
}
