package rangeTree;

import common.HorizontalLineSegment;
import common.QuickSelect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kgoyo on 15-12-2016.
 */
public class RangeTree2DNode {
    private boolean isLeaf = false;
    private HorizontalLineSegment leafPoint;
    private RangeTree1D assoc;
    private double ymid;
    private RangeTree2DNode lc;
    private RangeTree2DNode rc;

    public RangeTree2DNode(List<HorizontalLineSegment> segments) {
        assoc = new RangeTree1D(segments);
        if (segments.size() == 1) {
            isLeaf = true;
            leafPoint = segments.get(0);
        } else {
            List<Double> values = new ArrayList<>();
            values.addAll(segments.stream().map(HorizontalLineSegment::getX2).collect(Collectors.toList()));
            int medianIndex = QuickSelect.findMedian(values,0,segments.size()-1);
            ymid = values.get(medianIndex);
            List<HorizontalLineSegment> pLeft = new ArrayList<>();
            List<HorizontalLineSegment> pRight = new ArrayList<>();
            for (HorizontalLineSegment seg: segments) {
                if (seg.getX2() <= ymid) {
                    pLeft.add(seg);
                } else {
                    pRight.add(seg);
                }
            }
            lc = new RangeTree2DNode(pLeft);
            rc = new RangeTree2DNode(pRight);
        }
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public RangeTree1D getAssoc() {
        return assoc;
    }

    public double getYmid() {
        return ymid;
    }

    public RangeTree2DNode getLc() {
        return lc;
    }

    public RangeTree2DNode getRc() {
        return rc;
    }

    public HorizontalLineSegment getLeafPoint() {
        return leafPoint;
    }
}
