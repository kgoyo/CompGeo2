package kdTree;

/**
 * Created by kgoyo on 15-12-2016.
 */
public class KDTreeRegion {
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private double z1;
    private double z2;


    public KDTreeRegion(double x1, double x2, double y1, double y2, double z1, double z2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.z1 = z1;
        this.z2 = z2;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }

    public double getZ1() {
        return z1;
    }

    public double getZ2() {
        return z2;
    }
}
