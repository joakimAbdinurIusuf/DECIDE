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

    public boolean LIC1() throws IllegalParameterObjectException {
        if(parameters.getRADIUS1() < 0) {
            throw new IllegalParameterObjectException("RADIUS1 cannot be negative.");
        }

        for (int i = 0; i < numpoints - 2; i++) {
            if (!isPointsContainedInCircle(points[i], points[i+1], points[i+2], parameters.getRADIUS1())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if three points are all contained within or on a circle with a given radius
     *
     * @return true if all the points are contained within a circle with the given radius
     */
    public boolean isPointsContainedInCircle(double[] point1, double[] point2, double[] point3, double radius) {
        double distanceP1P2, distanceP1P3, distanceP2P3, triangleArea, diameter, maxDistance;

        distanceP1P2 = distanceBetween2Points(point1, point2);
        distanceP1P3 = distanceBetween2Points(point1, point3);
        distanceP2P3 = distanceBetween2Points(point2, point3);

        triangleArea = triangleArea(point1, point2, point3);

        if (triangleArea != 0) {
            diameter = (distanceP1P2 * distanceP1P3 * distanceP2P3) / (2 * triangleArea);

            if (diameter/2 <= radius) {
                return true;
            }
        }
        else {  // if area = 0, the points are collinear
            maxDistance = Math.max(distanceP1P2, Math.max(distanceP1P3, distanceP2P3));
            if (maxDistance <= radius) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if LIC2 is true.
     * Calculates the angle between three consecutive points, the second point being located at the vertex
     * of the angle.
     *
     * @return true if the angle < pi - epsilon or angle > pi + epsilon
     *
     */
    public boolean LIC2() throws IllegalParameterObjectException {
        if (parameters.getEPSILON() < 0 || parameters.getEPSILON() >= Math.PI) {
            throw new IllegalParameterObjectException("Epsilon must be greater than 0 and less than pi.");
        }

        double xDifference1, yDifference1, xDifference2, yDifference2, dotProduct, norm1, norm2, angle;

        for (int i = 1; i < numpoints - 1; i++) {
            if(!(points[i - 1] == points[i]) || (points[i + 1] == points[i])) {
                xDifference1 = points[i][0] - points[i - 1][0];
                yDifference1 = points[i][1] - points[i - 1][1];
                xDifference2 = points[i + 1][0] - points[i][0];
                yDifference2 = points[i + 1][1] - points[i][1];

                dotProduct = xDifference1 * xDifference2 + yDifference1 * yDifference2;
                norm1 = Math.sqrt(Math.pow(xDifference1, 2) + Math.pow(yDifference1, 2));
                norm2 = Math.sqrt(Math.pow(xDifference2, 2) + Math.pow(yDifference2, 2));

                angle = Math.acos(dotProduct / (norm1 * norm2));

                if (angle < (Math.PI - parameters.getEPSILON()) || angle > (Math.PI + parameters.getEPSILON())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if LIC3 is true.
     *
     * @return true if any three consecutive points forms a triangle with larger area than AREA1, false
     * otherwise
     */
    public boolean LIC3() throws IllegalParameterObjectException {
        if (parameters.getAREA1() < 0) {
            throw new IllegalParameterObjectException("AREA1 cannot be negative.");
        }

        double triangleArea;
        for (int i = 0; i < numpoints-2; i++){
            triangleArea = triangleArea(points[i], points[i+1], points[i+2]);

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
    public boolean LIC6() throws IllegalParameterObjectException {
        if (parameters.getDIST() < 0) {
            throw new IllegalParameterObjectException("DIST cannot be negative.");
        } else if (parameters.getN_PTS() < 3) {
            throw new IllegalParameterObjectException("N_PTS cannot be smaller than 3.");
        } else if (parameters.getN_PTS() > numpoints) {
            throw new IllegalParameterObjectException("N_PTS cannot be larger than number of data points.");
        } else if (numpoints < 3) {
            return false;
        }

        int N_PTS = parameters.getN_PTS();
        double[] firstP, lastP, currentP;
        int lastPIndex;
        double distance;

        for (int i = 0; i < numpoints-N_PTS+1; i++) {
            firstP = points[i];
            lastPIndex = i+N_PTS-1;
            lastP = points[lastPIndex];

            for (int j = i + 1; j < lastPIndex; j++) {
                currentP = points[j];

                if ((Double.compare(firstP[0], lastP[0]) == 0) && (Double.compare(firstP[1], lastP[1]) == 0)) {
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

    /** Check if LIC7 is true.
     *
     * @return true if there exists one set of two data points separated by exactly
     * K PTS consecutive intervening points that are a distance greater than the length, false otherwise.
     */
    public boolean LIC7() throws IllegalParameterObjectException {
        if (numpoints < 3) {
            return false;
        }
        throwExceptionsLIC7();

        int K_PTS = parameters.getK_PTS();
        double[] firstPoint, secondPoint;
        double LENGTH1 = parameters.getLENGTH1();

        for (int i = 0; i < numpoints - K_PTS - 1; i++) {
            firstPoint = points[i];
            secondPoint = points[i + K_PTS + 1];
            if (distanceBetween2Points(firstPoint, secondPoint) > LENGTH1) {
                return true;
            }
        }
        return false;
    }

    private void throwExceptionsLIC7() throws IllegalParameterObjectException {
        if (parameters.getK_PTS() < 1) {
            throw new IllegalParameterObjectException("K_PTS must be greater or equal to 1.");
        } else if (parameters.getK_PTS() > numpoints - 2) {
            throw new IllegalParameterObjectException("K_PTS must be less than or equal to numpoints - 2.");
        }
    }

    /**
     * Check if LIC10 is true.
     *
     * @return true if There exists at least one set of three data points separated by E_PTS and F_PTS consecutive
     * intervening points, that are the vertices of a triangle with area greater than AREA1, false otherwise.
     *
     * @throws IllegalParameterObjectException
     */
    public boolean LIC10() throws IllegalParameterObjectException {
        if (parameters.getE_PTS() < 1) {
            throw new IllegalParameterObjectException("E_PTS must be larger than or equal to 1.");
        } else if (parameters.getF_PTS() < 1) {
            throw new IllegalParameterObjectException("F_PTS must be larger than or equal to 1.");
        } else if (parameters.getE_PTS() + parameters.getF_PTS() > numpoints - 3) {
            throw new IllegalParameterObjectException("The sum of E_PTS and F_PTS must be less than or equal to numpoints - 3.");
        } else if (numpoints < 5) {
            return false;
        }

        int E_PTS = parameters.getE_PTS();
        int F_PTS = parameters.getF_PTS();
        int lastIndex = numpoints - E_PTS - F_PTS - 2;

        for (int i = 0; i < lastIndex; i++) {
            double triangleArea = triangleArea(points[i], points[i+E_PTS+1], points[i+E_PTS+F_PTS+2]);
            if (Double.compare(triangleArea, parameters.getAREA1()) > 0) {
                return true;
            }
        }
        return false;
    }

    /** Check if LIC12 is true
     *
     * @return true if there exists at least one or two sets of two data points, separated by K_PTS
     * consecutive intervening points, which are a distance greater than LENGTH1 apart, or a distance
     * less than LENGTH2 apart (both needs to be fulfilled), otherwise, return false.
     *
     * @throws IllegalParameterObjectException
     */
    public boolean LIC12() throws IllegalParameterObjectException{
        if (parameters.getLENGTH2() < 0) {
            throw new IllegalParameterObjectException("LENGTH2 cannot be negative.");
        } else if (numpoints < 3) {
            return false;
        }

        int K_PTS = parameters.getK_PTS();
        int lastIndex = numpoints - K_PTS - 1;
        boolean isLargerThanLENGTH1 = false;
        boolean isLessThanLENGTH2 = false;

        for (int i = 0; i < lastIndex; i++) {
            double distance = distanceBetween2Points(points[i], points[i+K_PTS+1]);

            if (Double.compare(distance, parameters.getLENGTH1()) > 0) {
                isLargerThanLENGTH1 = true;
            }
            if (Double.compare(distance, parameters.getLENGTH2()) < 0) {
                isLessThanLENGTH2 = true;
            }
            if (isLargerThanLENGTH1 && isLessThanLENGTH2) {
                return true;
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
     * Check if LIC14 is true.
     *
     * @return true if there exists at least one or two sets of three data points, separated by E_PTS and
     * F_PTS consecutive intervening points, so that they are the vertices of a triangle with area greater
     * than AREA1 or less than AREA2 (or both at the same time), false otherwise.
     *
     * @throws IllegalParameterObjectException
     */
    public boolean LIC14() throws IllegalParameterObjectException{
        if (parameters.getAREA2() < 0) {
            throw new IllegalParameterObjectException("AREA2 cannot be negative.");
        } else if (numpoints < 5) {
            return false;
        }

        int E_PTS = parameters.getE_PTS();
        int F_PTS = parameters.getF_PTS();
        int lastIndex = numpoints - E_PTS - F_PTS - 2;
        boolean isLargerThanAREA1 = false;
        boolean isLessThanAREA2 = false;

        for (int i = 0; i < lastIndex; i++) {
            double triangleArea = triangleArea(points[i], points[i+E_PTS+1], points[i+E_PTS+F_PTS+2]);

            if (Double.compare(triangleArea, parameters.getAREA1()) > 0) {
                isLargerThanAREA1 = true;
            }
            if (Double.compare(triangleArea, parameters.getAREA2()) < 0) {
                isLessThanAREA2 = true;
            }
            if (isLargerThanAREA1 && isLessThanAREA2) {
                return true;
            }
        }

        return false;
    }

    /**
     * Calculates the area of a triangle with the area formula:
     * |Ax(By-Cy)+Bx(Cy-Ay)+Cx(Ay-By)/2| for the three points A, B, and C.
     */
    public double triangleArea(double[] point1, double[] point2, double[]point3) {
        double firstTerm = point1[0] * (point2[1] - point3[1]);
        double secondTerm = point2[0] * (point3[1] - point1[1]);
        double thirdTerm = point3[0] * (point1[1] - point2[1]);

        return Math.abs(firstTerm + secondTerm + thirdTerm) / 2;
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
