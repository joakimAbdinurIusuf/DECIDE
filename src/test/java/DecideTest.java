import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DecideTest {

    private Parameters parameters;

    public DecideTest() {
        parameters = new Parameters();
    }

    @Test
    public void givenDistanceGreaterThanLENGTH1BetweenTwoConsecutivePoints_whenLIC0_thenAssertTrue() {
        double[][] points = new double[][]{{1.0D, 1.0D}, {100.0D, 100.0D}};
        int numpoints = points.length;
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC0True = decide.LIC0();
        assertTrue(LIC0True);
    }

    @Test
    public void givenDistanceGreaterThanLENGTH1DoesNotExistBetweenTwoConsecutivePoints_whenLIC0_thenAssertFalse() {
        double[][] points = new double[][]{{1.0D, 1.0D}, {1.1, 1.1}};
        int numpoints = points.length;
        Decide decide = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        boolean LIC0False = decide.LIC0();
        assertFalse(LIC0False);
    }
}
