package rangeTree;

import common.HorizontalLineSegment;
import common.QuickSelect;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kgoyo on 15-12-2016.
 */
public class RangeTree1DNode {

    private Double nodeValue;
    private HorizontalLineSegment leafPoint;
    private boolean isLeaf = false;
    private RangeTree1DNode lc;
    private RangeTree1DNode rc;

    public RangeTree1DNode(List<HorizontalLineSegment> segments) {
        if (segments.size() == 1) {
            isLeaf = true;
            leafPoint = segments.get(0);
        } else {
            //find median y (X2)
            List<Double> yValues = new ArrayList<>();
            yValues.addAll(segments.stream().map(HorizontalLineSegment::getX2).collect(Collectors.toList()));
            //find median
            nodeValue = yValues.get(QuickSelect.findMedian(yValues,0,yValues.size()-1));
            List<HorizontalLineSegment> lsegments = new ArrayList<>();
            List <HorizontalLineSegment> rsegments = new ArrayList<>();
            for (HorizontalLineSegment seg: segments) {
                if (seg.getX2() <= nodeValue) {
                    lsegments.add(seg);
                } else {
                    rsegments.add(seg);
                }
            }
            lc = new RangeTree1DNode(lsegments);
            rc = new RangeTree1DNode(rsegments);
        }
    }

    public Double getNodeValue() {
        return nodeValue;
    }

    public HorizontalLineSegment getLeafPoint() {
        return leafPoint;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public RangeTree1DNode getLc() {
        return lc;
    }

    public RangeTree1DNode getRc() {
        return rc;
    }
}
