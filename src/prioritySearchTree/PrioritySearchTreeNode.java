package prioritySearchTree;

import common.HorizontalLineSegment;
import common.QuickSelect;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kgoyo on 11-12-2016.
 */
public class PrioritySearchTreeNode {

    private boolean isLeaf = false;
    private HorizontalLineSegment pMin;
    private Double yMid;
    private PrioritySearchTreeNode lc;
    private PrioritySearchTreeNode rc;


    public PrioritySearchTreeNode(List<HorizontalLineSegment> segments, boolean leftQuery) {
        if (segments.size() == 0) {
            isLeaf = true;
            return;
        }
        //find pMin
        pMin = null;
        for (HorizontalLineSegment  seg: segments) {
            if (leftQuery) {
                if (pMin == null || pMin.getX1() > seg.getX1()) {
                    pMin = seg;
                }
            } else {
                if (pMin == null || pMin.getX2() < seg.getX2()) {
                    pMin = seg; //pmin is a maximum when the 3 sided query points right
                }
            }
        }
        //find ymid
        ArrayList<Double> yValues = segments.stream().map(HorizontalLineSegment::getY).collect(Collectors.toCollection(ArrayList::new));
        int medianIndex = QuickSelect.findMedian(yValues,0,yValues.size()-1);
        yMid = yValues.get(medianIndex);

        //split into below and above
        List<HorizontalLineSegment> below = new ArrayList<>();
        List<HorizontalLineSegment> above = new ArrayList<>();
        for (HorizontalLineSegment seg : segments) {
            if (seg == pMin) {
                continue;
            }
            if (seg.getY() < yMid) {
                below.add(seg);
            } else {
                above.add(seg);
            }
        }
        lc = new PrioritySearchTreeNode(below, leftQuery);
        rc = new PrioritySearchTreeNode(above, leftQuery);
    }

    public HorizontalLineSegment getpMin() {
        return pMin;
    }

    public Double getyMid() {
        return yMid;
    }

    public PrioritySearchTreeNode getLc() {
        return lc;
    }

    public PrioritySearchTreeNode getRc() {
        return rc;
    }

    public boolean isLeaf() {
        return isLeaf;
    }
}
