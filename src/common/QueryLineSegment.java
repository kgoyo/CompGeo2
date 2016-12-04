package common;

/**
 * Created by kgoyo on 01-12-2016.
 */


public class QueryLineSegment {

    private final double x;
    private final double y1;
    private final double y2;

    public double getX() {
        return x;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }

    public QueryLineSegment(double x, double y1, double y2) {

        this.x = x;
        this.y1 = y1;
        this.y2 = y2;
    }
}
