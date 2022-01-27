public class Decide {
    private Parameters parameters;
    private double[][] points;
    private int numpoints;

    public Decide(int numpoints, double[][] points, Parameters parameters, LCM lcm, boolean[] puv) {
        this.numpoints = numpoints;
        this.points = points;
        this.parameters = parameters;
    }

    public void DECIDE() {
        System.out.println("NO");
    }

    /**
     * Check if LIC0 is true.
     * Uses the distance formula to compute the distance between two consecutive data points.
     *
     * @return true if there exists two consecutive data points with a distance greater than LENGTH1,
     * false otherwise.
     */
    public boolean LIC0() {
        if (parameters.getLENGTH1() < 0) {
            return false;
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
}