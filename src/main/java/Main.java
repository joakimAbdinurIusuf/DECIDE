public class Main {

    public static void main(String[] var0) {
        double[][] points = new double[][]{{1.0, 1.0}, {100.0, 100.0}};
        Parameters parameters = new Parameters();
        int numpoints = points.length;
        Decide d = new Decide(numpoints, points, parameters, (LCM)null, (boolean[])null);
        d.DECIDE();
    }
}