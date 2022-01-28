import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DecideTest {

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
