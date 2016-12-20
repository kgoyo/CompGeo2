package rangeTree;

import common.HorizontalLineSegment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kgoyo on 15-12-2016.
 */
//2D range trees are for the y-coordinate
public class RangeTree2D {
    private RangeTree2DNode rootNode;

    public RangeTree2D(List<HorizontalLineSegment> segments) {
        rootNode = new RangeTree2DNode(segments);
    }

    //note x and y are swapped from what they are in the book
    public List<HorizontalLineSegment> rangeQuery2D(double x1, double x2, double y1, double y2) {
        List<HorizontalLineSegment> res = new ArrayList<>();

        RangeTree2DNode vsplit = findSplitNode(y1, y2);
        if (vsplit.isLeaf()) {
            reportIfInRange(vsplit.getLeafPoint(),x1,x2,y1,y2,res);
        } else {
            RangeTree2DNode v = vsplit.getLc();
            while (!v.isLeaf()) {
                if (y1 <= v.getYmid()) {
                    res.addAll(v.getRc().getAssoc().rangeQuery1D(x1,x2));
                    v = v.getLc();
                } else {
                    v = v.getRc();
                }
            }
            reportIfInRange(v.getLeafPoint(),x1,x2,y1,y2,res);
            v = vsplit.getRc();
            while (!v.isLeaf()) {
                if (y2 > v.getYmid()) {
                    res.addAll(v.getLc().getAssoc().rangeQuery1D(x1,x2));
                    v = v.getRc();
                } else {
                    v = v.getLc();
                }
            }
            reportIfInRange(v.getLeafPoint(),x1,x2,y1,y2,res);
        }

        return res;
    }

    private void reportIfInRange(HorizontalLineSegment point, double x1, double x2, double y1, double y2, List<HorizontalLineSegment> res) {
        if (point.getX1() >= x1 &&
            point.getX1() <= x2 &&
            point.getX2() >= y1 &&
            point.getX2() <= y2) {
            res.add(point);
        }
    }

    private RangeTree2DNode findSplitNode( double y1, double y2) {
        RangeTree2DNode v = rootNode;
        while ((!v.isLeaf()) && (y2 <= v.getYmid() || y1 > v.getYmid())) {
            if (y2 <= v.getYmid()) {
                v = v.getLc();
            } else {
                v = v.getRc();
            }
        }
        return v;
    }
}
