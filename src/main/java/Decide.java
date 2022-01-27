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

    private boolean LIC0() {
        if (parameters.getLENGTH1() < 0) {
            return false;
        }

        for(int i = 0; i < numpoints - 1; i++) {
            double xDifference = Math.abs(points[i + 1][0] - points[i][0]);
            double yDifference = Math.abs(points[i + 1][1] - points[i][1]);
            double distance = Math.sqrt(xDifference * xDifference + yDifference * yDifference);
            if (distance > parameters.getLENGTH1()) {
                return true;
            }
        }
        return false;
    }
}