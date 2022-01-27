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

        for (int i = 0; i < numpoints - 1; i++) {
            double xDifference = Math.abs(points[i + 1][0] - points[i][0]);
            double yDifference = Math.abs(points[i + 1][1] - points[i][1]);
            double distance = Math.sqrt(xDifference * xDifference + yDifference * yDifference);
            if (distance > parameters.getLENGTH1()) {
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
    public boolean LIC3() {
        if (parameters.getAREA1() < 0) {
            return false;
        }

        double triangleArea;

        for (int i = 0; i < numpoints-2; i++){
            triangleArea = Math.abs((points[i][0] * (points[i+1][1] - points[i+2][1]) +
                                    points[i+1][0] * (points[i+2][1] - points[i][1]) +
                                    points[i+2][0] * (points[i][1] - points[i+2][1])) / 2);

            if (Double.compare(triangleArea, parameters.getAREA1()) > 0) {
                return true;
            }
        }
        return false;
    }
}
