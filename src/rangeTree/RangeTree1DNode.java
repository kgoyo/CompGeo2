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
            //find median x (X1)
            List<Double> xValues = new ArrayList<>();
            xValues.addAll(segments.stream().map(HorizontalLineSegment::getX1).collect(Collectors.toList()));
            //find median
            int medianIndex = QuickSelect.findMedian(xValues,0,xValues.size()-1);
            nodeValue = xValues.get(medianIndex);

            List<HorizontalLineSegment> lsegments = new ArrayList<>();
            List <HorizontalLineSegment> rsegments = new ArrayList<>();
            for (HorizontalLineSegment seg: segments) {
                if (seg.getX1() <= nodeValue) {
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
