package e.rick.duolingoclone.Model;

/**
 * Created by Rick on 3/3/2018.
 */

public class PairModel {

    String pair1;
    String pair2;

    public PairModel(String pair1, String pair2) {
        this.pair1 = pair1;
        this.pair2 = pair2;
    }

    public String getPair1() {
        return pair1;
    }

    public void setPair1(String pair1) {
        this.pair1 = pair1;
    }

    public String getPair2() {
        return pair2;
    }

    public void setPair2(String pair2) {
        this.pair2 = pair2;
    }

    @Override
    public String toString() {
        return "PairModel{" +
                "pair1='" + pair1 + '\'' +
                ", pair2='" + pair2 + '\'' +
                '}';
    }
}
