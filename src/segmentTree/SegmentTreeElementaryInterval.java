package segmentTree;

/**
 * Created by kgoyo on 01-12-2016.
 */
public class SegmentTreeElementaryInterval {
    private final double x1;
    private final double x2;

    public SegmentTreeElementaryInterval(double x1, double x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    @Override
    public String toString() {
        return "\u001B[31m["+x1+","+x2+"]\u001B[0m";
    }
}
