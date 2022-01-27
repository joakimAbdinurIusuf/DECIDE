public class Parameters {
    private double LENGTH1;
    private double RADIUS1;
    private double EPSILON;
    private double AREA1;
    private int Q_PTS;
    private int QUADS;
    private double DIST;
    private int N_PTS;
    private int K_PTS;
    private int A_PTS;
    private int B_PTS;
    private int C_PTS;
    private int D_PTS;
    private int E_PTS;
    private int F_PTS;
    private int G_PTS;
    private double LENGTH2;
    private double RADIUS2;
    private double AREA2;

    public Parameters(double LENGTH1) {
        this.LENGTH1 = LENGTH1;
    }

    public Parameters(double l1, double r1, double ep, double a1, int qp, int q, double dist, int n, int k, int a, int b, int c, int d, int e, int f, int g, double l2, double r2, double a2) {
        this.LENGTH1 = l1;
        this.RADIUS1 = r1;
        this.EPSILON = ep;
        this.AREA1 = a1;
        this.Q_PTS = qp;
        this.QUADS = q;
        this.DIST = dist;
        this.N_PTS = n;
        this.K_PTS = k;
        this.A_PTS = a;
        this.B_PTS = b;
        this.C_PTS = c;
        this.D_PTS = d;
        this.E_PTS = e;
        this.F_PTS = f;
        this.G_PTS = g;
        this.LENGTH2 = l2;
        this.RADIUS2 = r2;
        this.AREA2 = a2;
    }

    public double getLENGTH1() {
        return LENGTH1;
    }

    public double getRADIUS1() {
        return RADIUS1;
    }

    public double getEPSILON() {
        return EPSILON;
    }

    public double getAREA1() {
        return AREA1;
    }

    public int getQ_PTS() {
        return Q_PTS;
    }

    public int getQUADS() {
        return QUADS;
    }

    public double getDIST() {
        return DIST;
    }

    public int getN_PTS() {
        return N_PTS;
    }

    public int getK_PTS() {
        return K_PTS;
    }

    public int getA_PTS() {
        return A_PTS;
    }

    public int getB_PTS() {
        return B_PTS;
    }

    public int getC_PTS() {
        return C_PTS;
    }

    public int getD_PTS() {
        return D_PTS;
    }

    public int getE_PTS() {
        return E_PTS;
    }

    public int getF_PTS() {
        return F_PTS;
    }

    public int getG_PTS() {
        return G_PTS;
    }

    public double getLENGTH2() {
        return LENGTH2;
    }

    public double getRADIUS2() {
        return RADIUS2;
    }

    public double getAREA2() {
        return AREA2;
    }

}