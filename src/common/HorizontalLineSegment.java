package common;

public class HorizontalLineSegment implements Comparable<HorizontalLineSegment> {
    private final double x1;
    private final double x2;
    private final double y;

    public HorizontalLineSegment (double x1, double x2, double y) {
        this.x1 = x1;
        this.x2 = x2;
        this.y = y;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "("+x1+","+x2+")";
    }

    @Override
    public int compareTo(HorizontalLineSegment o) {
        Double yy = y;
        return yy.compareTo(o.getY());
    }
}
