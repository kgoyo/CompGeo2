package rangeTree;

import common.HorizontalLineSegment;
import common.QuickSelect;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kgoyo on 20-12-2016.
 */
public class RangeTree3DNode {
    private RangeTree3DNode lc;
    private RangeTree3DNode rc;
    private double zmid;
    private HorizontalLineSegment leafPoint;
    private boolean isLeaf = false;
    private RangeTree2D assoc;

    public RangeTree3DNode(List<HorizontalLineSegment> segments) {
        assoc = new RangeTree2D(segments);
        if (segments.size() == 1) {
            isLeaf = true;
            leafPoint = segments.get(0);
        } else {
            List<Double> values = new ArrayList<>();
            values.addAll(segments.stream().map(HorizontalLineSegment::getY).collect(Collectors.toList()));
            zmid = segments.get(QuickSelect.findMedian(values,0,segments.size()-1)).getY();
            List<HorizontalLineSegment> pLeft = new ArrayList<>();
            List<HorizontalLineSegment> pRight = new ArrayList<>();
            for (HorizontalLineSegment seg: segments) {
                if (seg.getY() <= zmid) {
                    pLeft.add(seg);
                } else {
                    pRight.add(seg);
                }
            }
            lc = new RangeTree3DNode(pLeft);
            rc = new RangeTree3DNode(pRight);
        }
    }

    public RangeTree3DNode getLc() {
        return lc;
    }

    public RangeTree3DNode getRc() {
        return rc;
    }

    public double getZmid() {
        return zmid;
    }

    public HorizontalLineSegment getLeafPoint() {
        return leafPoint;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public RangeTree2D getAssoc() {
        return assoc;
    }
}
