public class Main {
    public static void main(String[] var0) {
        double[][] points = new double[][]{{1.0D, 1.0D}, {100.0D, 100.0D}};
        Parameters parameters = new Parameters();
        Decide d = new Decide(10, points, parameters, (LCM)null, (boolean[])null);
        d.DECIDE();
    }
}