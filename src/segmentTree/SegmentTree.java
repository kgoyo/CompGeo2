package segmentTree;

import common.HorizontalLineSegment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SegmentTree {
    private final SegmentTreeNode rootNode;

    public SegmentTree (List<HorizontalLineSegment> list) {

        //construct elementary intervals
        List<Double> endPoints = new ArrayList<Double>();
        for (HorizontalLineSegment ls : list) {
            endPoints.add(ls.getX1());
            endPoints.add(ls.getX2());
        }
        Collections.sort(endPoints);
        List<SegmentTreeElementaryInterval> elementaryIntervals = new ArrayList<>();

        for (int i=0; i<endPoints.size(); i++) {
            SegmentTreeElementaryInterval interval1;
            if (i==0) {
                interval1 = new SegmentTreeElementaryInterval(Double.MIN_VALUE,endPoints.get(i));
            } else {
                interval1 = new SegmentTreeElementaryInterval(endPoints.get(i-1),endPoints.get(i));
            }
            SegmentTreeElementaryInterval interval2 = new SegmentTreeElementaryInterval(endPoints.get(i),endPoints.get(i));
            elementaryIntervals.add(interval1);
            elementaryIntervals.add(interval2);
        }

        //construct and set intervals for all nodes.
        rootNode = new SegmentTreeNode(elementaryIntervals);

        //insert intervals and get canonical subsets for all intervals
        for (HorizontalLineSegment segment : list) {
            insert(rootNode,segment);
        }

    }

    private void insert(SegmentTreeNode v, HorizontalLineSegment segment) {
        //might need to flip <= and >=
        if (v.getInterval().getX1() >= segment.getX1() && v.getInterval().getX2() <= segment.getX2()) {
            v.store(segment);
        } else {
            if (notEmptyIntersection(v.getLeft().getInterval(),segment)) {
                //TODO do stuff
            }
        }
    }

    private boolean notEmptyIntersection(SegmentTreeElementaryInterval interval, HorizontalLineSegment segment) {
        if (interval.getX1() <= segment.getX2() && segment.getX1() <= interval.getX1()) {
            return true;
        }
        if (interval.getX2() <= segment.getX2() && segment.getX1() <= interval.getX2()) {
            return true;
        }
        return false;
    }
}
