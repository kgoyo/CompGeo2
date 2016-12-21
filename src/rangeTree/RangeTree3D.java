package rangeTree;

import common.HorizontalLineSegment;
import common.QueryLineSegment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kgoyo on 20-12-2016.
 */
//3D range trees are for the z coordinate
public class RangeTree3D {
    private RangeTree3DNode rootNode;

    public RangeTree3D(List<HorizontalLineSegment> segments) {
        rootNode = new RangeTree3DNode(segments);
    }

    public List<HorizontalLineSegment> rangeQuery3D(QueryLineSegment queryLine) {

        //specializing the algorithm for our querylines
        double x1 = Double.MIN_VALUE;
        double x2 = queryLine.getX();
        double y1 = queryLine.getX();
        double y2 = Double.MAX_VALUE;
        double z1 = queryLine.getY1();
        double z2 = queryLine.getY2();

        List<HorizontalLineSegment> res = new ArrayList<>();

        RangeTree3DNode vsplit = findSplitNode(z1,z2);
        if (vsplit.isLeaf()) {
            reportIfInRange(vsplit.getLeafPoint(),x1,x2,y1,y2,z1,z2,res);
        } else {
            RangeTree3DNode v = vsplit.getLc();
            while (!v.isLeaf()) {
                if (z1 <= v.getZmid()) {
                    res.addAll(v.getRc().getAssoc().rangeQuery2D(x1,x2,y1,y2));
                    v = v.getLc();
                } else {
                    v = v.getRc();
                }
            }
            reportIfInRange(v.getLeafPoint(),x1,x2,y1,y2,z1,z2,res);

            v = vsplit.getRc();
            while (!v.isLeaf()) {
                if (z2 > v.getZmid()) {
                    res.addAll(v.getLc().getAssoc().rangeQuery2D(x1,x2,y1,y2));
                    v = v.getRc();
                } else {
                    v = v.getLc();
                }
            }
            reportIfInRange(v.getLeafPoint(),x1,x2,y1,y2,z1,z2,res);
        }

        return res;
    }

    private void reportIfInRange(HorizontalLineSegment point, double x1, double x2, double y1, double y2, double z1, double z2, List<HorizontalLineSegment> res) {
        if (point.getX1() >= x1 &&
            point.getX1() <= x2 &&
            point.getX2() >= y1 &&
            point.getX2() <= y2 &&
            point.getY() >= z1 &&
            point.getY() <= z2) {
            res.add(point);
        }
    }

    private RangeTree3DNode findSplitNode(double z1, double z2) {
        RangeTree3DNode v = rootNode;
        while ((!v.isLeaf()) && (z2 <= v.getZmid() || z1 > v.getZmid())) {
            if (z2 <= v.getZmid()) {
                v = v.getLc();
            } else {
                v = v.getRc();
            }
        }
        return v;
    }
}
