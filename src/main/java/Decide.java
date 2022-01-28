public class Decide {
    private Parameters parameters;
    private double[][] points;
    private int numpoints;
    private LCM lcm;

    public Decide(int numpoints, double[][] points, Parameters parameters, LCM lcm, boolean[] puv) {
        this.numpoints = numpoints;
        this.points = points;
        this.parameters = parameters;
        this.lcm = lcm;
    }

    public void DECIDE() {
        try {
            // Code to call LIC-methods will be added later
            System.out.println("NO");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage() );
        }

    }

    /**
     * Check if LIC0 is true.
     * Uses the distance formula to compute the distance between two consecutive data points.
     *
     * @return true if there exists two consecutive data points with a distance greater than LENGTH1,
     * false otherwise.
     */
    public boolean LIC0() throws IllegalArgumentException {
        if (parameters.getLENGTH1() < 0) {
            throw new IllegalArgumentException("LENGTH1 cannot be negative.");
        }

        double xDifference;
        double yDifference;
        double distance;

        for (int i = 0; i < numpoints - 1; i++) {
            xDifference = Math.abs(points[i + 1][0] - points[i][0]);
            yDifference = Math.abs(points[i + 1][1] - points[i][1]);
            distance = Math.sqrt(xDifference * xDifference + yDifference * yDifference);

            if (Double.compare(distance, parameters.getLENGTH1()) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if LIC3 is true.
     * Calculates the area of every triangle given by three consecutive data points. The area is calculated
     * with the area formula |Ax(By-Cy)+Bx(Cy-Ay)+Cx(Ay-By)/2| for the three consecutive points A, B, and C.
     *
     * @return true if any three consecutive points forms a triangle with larger area than AREA1, false
     * otherwise
     */
    public boolean LIC3() throws IllegalArgumentException {
        if (parameters.getAREA1() < 0) {
            throw new IllegalArgumentException("AREA1 cannot be negative.");
        }

        double triangleArea;
        double firstTerm;
        double secondTerm;
        double thirdTerm;

        for (int i = 0; i < numpoints-2; i++){
            firstTerm = points[i][0] * (points[i+1][1] - points[i+2][1]);
            secondTerm = points[i+1][0] * (points[i+2][1] - points[i][1]);
            thirdTerm = points[i+2][0] * (points[i][1] - points[i+1][1]);

            triangleArea = Math.abs(firstTerm + secondTerm + thirdTerm)/2;

            if (Double.compare(triangleArea, parameters.getAREA1()) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if LIC5 is true.
     *
     * @return true if there exists two consecutive data points such that X_i+1 - X_i < 0
     */
    public boolean LIC5() {
        double xDifference;

        for(int i = 0; i < numpoints - 1; i++) {
            xDifference = points[i + 1][0] - points[i][0];
            if (xDifference < 0) {
                return true;
            }
        }
        return false;
    }

    public boolean[][] PUM(boolean[] CMV) {
        boolean[][] PUM;
        PUM = new boolean[CMV.length][CMV.length];
        for (int i = 0; i < CMV.length; i++) {
            for (int j = 0; j < CMV.length; j++) {
                PUM[i][j] = lcm.operate(i, j, CMV);
            }
        }
        return PUM;
    }

}
