package rangeTree;

import common.HorizontalLineSegment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kgoyo on 15-12-2016.
 */
//1D range trees is for the x-coordinate
public class RangeTree1D {
    private final RangeTree1DNode rootNode;

    //segments is expected to be sorted
    public RangeTree1D(List<HorizontalLineSegment> segments) {
        rootNode = new RangeTree1DNode(segments);
    }

    private RangeTree1DNode findSplitNode(double x1, double x2) {
        RangeTree1DNode v = rootNode;
        while ((!v.isLeaf()) && (x2 <= v.getNodeValue() || x1 > v.getNodeValue())) {
            if (x2 <= v.getNodeValue()) {
                v = v.getLc();
            } else {
                v = v.getRc();
            }
        }
        return v;
    }

    public List<HorizontalLineSegment> rangeQuery1D(double x1, double x2) {
        List<HorizontalLineSegment> res = new ArrayList<>();
        RangeTree1DNode vsplit = findSplitNode(x1,x2);
        if (vsplit.isLeaf()) {
            reportIfInRange(vsplit.getLeafPoint(), x1, x2, res);
        } else {
            RangeTree1DNode v = vsplit.getLc();
            while (!v.isLeaf()) {
                if (x1 <= v.getNodeValue()) {
                    reportSubTree(v.getRc(),res);
                    v = v.getLc();
                } else {
                    v = v.getRc();
                }
            }
            reportIfInRange(v.getLeafPoint(),x1,x2,res);

            //same as above but Lc and Rc is swapped
            v = vsplit.getRc();
            while (!v.isLeaf()) {
                if (x2 > v.getNodeValue()) {
                    reportSubTree(v.getLc(),res);
                    v = v.getRc();
                } else {
                    v = v.getLc();
                }
            }
            reportIfInRange(v.getLeafPoint(),x1,x2,res);
        }
        return res;
    }

    private void reportIfInRange(HorizontalLineSegment point, double x1, double x2, List<HorizontalLineSegment> res) {
        if (point.getX1() >= x1 && point.getX1() <= x2) {
            res.add(point);
        }
    }

    private void reportSubTree(RangeTree1DNode node, List<HorizontalLineSegment> res) {
        if (node.isLeaf()) {
            res.add(node.getLeafPoint());
        } else {
            reportSubTree(node.getLc(),res);
            reportSubTree(node.getRc(),res);
        }
    }
}
