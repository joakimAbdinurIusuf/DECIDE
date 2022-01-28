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

    /**
     * Check if LIC6 is true.
     * Uses formula for calculating distance between a line (given in two points firstP and lastP)
     * and another point currentP.
     *
     * @return true if there exists a set of N_PTS consecutive points where the distance between
     * a data point to the line joining the first and last point is greater than DIST, false
     * otherwise.
     */
    public boolean LIC6() throws IllegalArgumentException {
        if (parameters.getDIST() < 0) {
            throw new IllegalArgumentException("DIST cannot be negative.");
        } else if (parameters.getN_PTS() < 3) {
            throw new IllegalArgumentException("N_PTS cannot be smaller than 3.");
        } else if (parameters.getN_PTS() > numpoints) {
            throw new IllegalArgumentException("N_PTS cannot be larger than number of data points.");
        } else if (numpoints < 3) {
            return false;
        }

        int N_PTS = parameters.getN_PTS();
        double[] firstP;
        double[] lastP;
        int lastPIndex;
        double[] currentP;
        double numerator;
        double denominator;
        double distance;
        double xDifference;
        double yDifference;

        for (int i = 0; i < numpoints-(N_PTS-1); i++) {
            firstP = points[i];
            lastPIndex = i+N_PTS-1;
            lastP = points[lastPIndex];

            for (int j = i + 1; j < lastPIndex; j++) {
                currentP = points[j];

                if ((Double.compare(firstP[0],lastP[0])==0) & (Double.compare(firstP[1],lastP[1])==0)) {
                    distance = distanceBetween2Points(firstP,currentP);
                } else {
                    numerator = Math.abs((lastP[0] - firstP[0]) * (firstP[1] - currentP[1]) - (firstP[0] - currentP[0]) * (lastP[1] - firstP[1]));
                    denominator = Math.sqrt(Math.pow(lastP[0] - firstP[0], 2) + Math.pow(lastP[1] - firstP[1], 2));
                    distance = numerator / denominator;
                }

                if (Double.compare(distance, parameters.getDIST()) > 0) {
                    return true;
                }
            }
            }

        return false;
    }

    public double distanceBetween2Points(double[] point1, double[] point2) {
        double xDifference = Math.abs(point2[0] - point1[0]);
        double yDifference = Math.abs(point2[1] - point1[1]);

        return Math.sqrt(xDifference * xDifference + yDifference * yDifference);
    }

}
