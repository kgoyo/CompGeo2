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

    public boolean doesIntersectX(HorizontalLineSegment other) {
        if (other.getX1() > x) {
            return false;
        }
        if (other.getX2() < x) {
            return false;
        }/*
        if (y1 > other.getY()) {
            return false;
        }
        if (y2 < other.getY()) {
            return false;
        }
        */
        return true;
    }

    public boolean isInRange3D(HorizontalLineSegment other) {
        if (x < other.getX1()) {
            return false;
        }
        if (x > other.getX2()) {
            return false;
        }
        if (y1 > other.getY()) {
            return false;
        }
        if (y2 < other.getY()) {
            return false;
        }
        return true;
    }
}
