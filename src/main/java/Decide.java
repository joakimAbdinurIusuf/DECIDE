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
    public boolean LIC0() throws IllegalParameterObjectException {
        if (parameters.getLENGTH1() < 0) {
            throw new IllegalParameterObjectException("LENGTH1 cannot be negative.");
        }

        double xDifference, yDifference, distance;

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
    public boolean LIC3() throws IllegalParameterObjectException {
        if (parameters.getAREA1() < 0) {
            throw new IllegalParameterObjectException("AREA1 cannot be negative.");
        }

        double firstTerm, secondTerm, thirdTerm, triangleArea;

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
     * Check if LIC4 is true.
     *
     * @return true if there exists at least one set of Q_PTS consecutive data points
     * that lie in more than QUADS quadrants, false otherwise.
     */
    public boolean LIC4() throws IllegalParameterObjectException {
        throwExceptionsLIC4();

        for (int i = 0; i <= numpoints - parameters.getQ_PTS(); i++) {
            int isInFirstQuad = 0, isInSecondQuad = 0, isInThirdQuad = 0, isInFourthQuad = 0;
            int numOfQuadrantsThatContainPoint = 0;

            for (int j = 0; j < parameters.getQ_PTS(); j++) {
                if (dataPointIsInFirstQuadrant(i, j)) {
                    isInFirstQuad = 1;
                } else if (dataPointIsInSecondQuadrant(i, j)) {
                    isInSecondQuad = 1;
                } else if (dataPointIsInThirdQuadrant(i, j)) {
                    isInThirdQuad = 1;
                } else if (dataPointIsInFourthQuadrant(i, j)) {
                    isInFourthQuad = 1;
                }
            }
            numOfQuadrantsThatContainPoint = isInFirstQuad + isInSecondQuad + isInThirdQuad + isInFourthQuad;

            if (numOfQuadrantsThatContainPoint > parameters.getQUADS()) {
                return true;
            }
        }
        return false;
    }

    // If (x>0, y<0) then the data point is in quadrant 4.
    private boolean dataPointIsInFourthQuadrant(int i, int j) {
        return points[i + j][0] > 0 && points[i + j][1] < 0;
    }

    // If (0, y<0) or (x<0, y<0) then the data point is in quadrant 3.
    private boolean dataPointIsInThirdQuadrant(int i, int j) {
        return points[i + j][0] <= 0 && points[i + j][1] < 0;
    }

    // If (x<0, 0) or (x<0, y>0) then the data point is in quadrant 2.
    private boolean dataPointIsInSecondQuadrant(int i, int j) {
        return points[i + j][0] < 0 && points[i + j][1] >= 0;
    }

    // If (0, 0) or (x>0, 0) or (0, y>0) or (x>0, y>0) then the data point is in quadrant 1.
    private boolean dataPointIsInFirstQuadrant(int i, int j) {
        return points[i + j][0] >= 0 && points[i + j][1] >= 0;
    }

    private void throwExceptionsLIC4() throws IllegalParameterObjectException {
        if (parameters.getQ_PTS() < 2) {
            throw new IllegalParameterObjectException("Q_PTS must be greater than or equal to 2.");
        }
        if (parameters.getQ_PTS() > numpoints) {
            throw new IllegalParameterObjectException("Q_PTS must be less than than or equal to numpoints.");
        }
        if (parameters.getQUADS() < 1) {
            throw new IllegalParameterObjectException("QUADS must be greater than or equal to 1.");
        }
        if (parameters.getQUADS() > 3) {
            throw new IllegalParameterObjectException("QUADS must be less than than or equal to 3.");
        }
    }

    /**
     * Check if LIC5 is true.
     *
     * @return true if there exists two consecutive data points such that X_i+1 - X_i < 0
     */
    public boolean LIC5() {
        double xDifference;

        for (int i = 0; i < numpoints - 1; i++) {
            xDifference = points[i + 1][0] - points[i][0];
            if (xDifference < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if LIC6 is true.
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
        double[] firstP, lastP, currentP;
        int lastPIndex;
        double distance;

        for (int i = 0; i < numpoints-(N_PTS-1); i++) {
            firstP = points[i];
            lastPIndex = i+N_PTS-1;
            lastP = points[lastPIndex];

            for (int j = i + 1; j < lastPIndex; j++) {
                currentP = points[j];

                if ((Double.compare(firstP[0],lastP[0])==0) & (Double.compare(firstP[1],lastP[1])==0)) {
                    distance = distanceBetween2Points(firstP, currentP);
                } else {
                    distance = distanceBetweenLineAndPoint(firstP, lastP, currentP);
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

    /**
     Calculates distance between line and point given two points on the line
     *
     * @param linePoint1 one of the points on the line L
     * @param linePoint2 the other point on line L
     * @param point the point p
     *
     * @return the distance between line L and point p
     */
    public double distanceBetweenLineAndPoint(double[] linePoint1, double[] linePoint2, double[] point) {
        double numerator = Math.abs((linePoint2[0] - linePoint1[0]) * (linePoint1[1] - point[1]) - (linePoint1[0] - point[0]) * (linePoint2[1] - linePoint1[1]));
        double denominator = Math.sqrt(Math.pow(linePoint2[0] - linePoint1[0], 2) + Math.pow(linePoint2[1] - linePoint1[1], 2));
        double distance = numerator / denominator;

        return distance;
    }

     /* Check if LIC7 is true.
     *
     * @return true if there exists two consecutive data points such that X_i+1 - X_i < 0
     */
    public boolean LIC7() {
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
