import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class MainTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * Sets up a bytestream as standard output to be able to check the output of the program.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    /**
     * Checks so that if the PUV is all false, the launch function prints YES to the standard output
     * @throws IllegalParameterObjectException
     */
    @Test
    public void DECIDEPositive() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3);
        Logic[][] m = new Logic[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                m[i][j] = Logic.NOTUSED;
            }
        }
        LCM lcm = new LCM(m);
        boolean[] puv = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
        Decide decide = new Decide(numpoints, points, parameters, lcm, puv);
        decide.DECIDE();
        assertEquals("YES", output.toString().trim());
    }

    /**
     * Checks so that if the PUV is true, the LCM contains only ANDD operations, 
     * but not all the LIC are met, the DECIDE function prints NO to the standard output.
     * @throws IllegalParameterObjectException
     */
    @Test
    public void DECIDENegative() throws IllegalParameterObjectException {
        double[][] points = new double[][]{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}};
        int numpoints = points.length;
        Parameters parameters = new Parameters(3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3);
        Logic[][] m = new Logic[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                m[i][j] = Logic.ANDD;
            }
        }
        LCM lcm = new LCM(m);
        boolean[] puv = new boolean[]{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Decide decide = new Decide(numpoints, points, parameters, lcm, puv);
        decide.DECIDE();
        assertEquals("NO", output.toString().trim());
    }

    /**
     * Checks so that if DECIDE receives more than 101 data points it throws an exception.
     * @throws IllegalParameterObjectException
     */
    @Test
    public void DECIDEException() throws IllegalParameterObjectException {
        double[][] points = new double[101][2];
        for (int i = 0; i < 101; i ++) {
            for (int j = 0; j < 2; j++) {
                points[i][j] = 0.0;
            }
        }
        int numpoints = points.length;
        Parameters parameters = new Parameters(3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3);
        Logic[][] m = new Logic[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                m[i][j] = Logic.ANDD;
            }
        }
        LCM lcm = new LCM(m);
        boolean[] puv = new boolean[]{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Decide decide = new Decide(numpoints, points, parameters, lcm, puv);
        Exception exception = assertThrows(IllegalParameterObjectException.class, () -> {
            decide.DECIDE();
        });

        String expectedMessage = "Too many datapoints.\n";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Resets the standard output after tests are done.
     */
    @AfterEach
    public void breakDown() {
        System.setOut(System.out);
    }
}

