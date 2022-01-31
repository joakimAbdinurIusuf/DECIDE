public class LCM {
    private Logic[][] lcm;
    public LCM(Logic[][] lcm) {
        this.lcm = lcm;
    }

    public boolean operate(int i, int j, boolean[] CMV) {
        boolean res;
        switch (lcm[i][j]) {
            case ANDD:
                res = CMV[i] && CMV[j];
                break;

            case ORR: 
                res = CMV[i] || CMV[j];
                break;

            case NOTUSED:
                res = true;
                break;
                
            default: 
                throw new IllegalArgumentException();
        }
        return res;
    }

    public int size() throws IllegalParameterObjectException {
        if (lcm.length != lcm[0].length) {
            throw new IllegalParameterObjectException("LCM does not have the right dimensions.\n");
        }
        return lcm.length;
    }
}