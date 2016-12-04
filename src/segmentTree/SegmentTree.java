package segmentTree;

import common.HorizontalLineSegment;
import common.QueryLineSegment;

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
            if (notEmptyIntersection(v.getLeft().getInterval(), segment)) {
                insert(v.getLeft(), segment);
            }
            if (notEmptyIntersection(v.getRight().getInterval(), segment)) {
                insert(v.getRight(), segment);
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

    private List<HorizontalLineSegment> query(SegmentTreeNode v, QueryLineSegment queryLine) {
        //report all intervals n I(v) TODO hvordan skal det her lige forsåes det er vel ikke bare returner alt for så returnerer vi det hele
        if (v.getI().size() == 1) { //size is 1 when it is a leaf
            SegmentTreeElementaryInterval leftInterval = v.getLeft().getInterval();
            if (queryLine.getX() <= leftInterval.getX2() && queryLine.getX() >= leftInterval.getX1()) {
                query(v.getLeft(),queryLine);
            } else {
                query(v.getRight(),queryLine);
            }
        }
        return null; //FIXME
    }

    public List<HorizontalLineSegment> querySegmentTree(QueryLineSegment queryLine) {
        return query(rootNode, queryLine);
    }


}
