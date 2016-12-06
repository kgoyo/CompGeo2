package segmentTree;

import common.HorizontalLineSegment;
import common.QueryLineSegment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SegmentTree {
    private final SegmentTreeNode rootNode;
    private LinkedList<HorizontalLineSegment> res;

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
             if (i>0) {
                 SegmentTreeElementaryInterval interval = new SegmentTreeElementaryInterval(endPoints.get(i-1),endPoints.get(i));
                elementaryIntervals.add(interval);
            }
        }

        //construct and set intervals for all nodes.
        rootNode = new SegmentTreeNode(elementaryIntervals);

        //insert intervals and get canonical subsets for all intervals
        for (HorizontalLineSegment segment : list) {
            insert(rootNode,segment);
        }

    }

    private void insert(SegmentTreeNode v, HorizontalLineSegment segment) {
        if (v.getInterval().getX1() >= segment.getX1() && v.getInterval().getX2() <= segment.getX2()) {
            v.store(segment);
        } else {

            if (v.getLeft()!= null) {
                if (notEmptyIntersection(v.getLeft().getInterval(), segment)) {
                    insert(v.getLeft(), segment);
                }
            }
            if (v.getRight() != null) {
                if (notEmptyIntersection(v.getRight().getInterval(), segment)) {
                    insert(v.getRight(), segment);
                }
            }

            /*
            if (v.getLeft()!= null) insert(v.getLeft(), segment);
            if (v.getRight()!= null) insert(v.getRight(), segment);
            */
        }
    }

    private boolean notEmptyIntersection(SegmentTreeElementaryInterval interval, HorizontalLineSegment segment) {
        /*
        if (interval.getX1() <= segment.getX2() && segment.getX1() <= interval.getX1()) {
            return true;
        }
        if (interval.getX2() <= segment.getX2() && segment.getX1() <= interval.getX2()) {
            return true;
        }

        if (segment.getX1() <= interval.getX2() && interval.getX1() <= segment.getX1()) {
            return true;
        }
        if (segment.getX2() <= interval.getX2() && interval.getX1() <= segment.getX2()) {
            return true;
        }
        */

        if (segment.getX1() >= interval.getX1() && segment.getX2() <= interval.getX2()) {
            return true;
        }
        if (segment.getX1() <= interval.getX1() && segment.getX2() >= interval.getX2()) {
            return true;
        }
        if (segment.getX1() <= interval.getX1() && segment.getX2() >= interval.getX1()) {
            return true;
        }
        if (segment.getX1() <= interval.getX2() && segment.getX2() >= interval.getX2()) {
            return true;
        }
        return false;
    }

    private void query(SegmentTreeNode v, QueryLineSegment queryLine) {
         res.addAll(v.getI());
        if (v.getLeft() != null || v.getRight() != null) {
        //if (v.getI().size() > 1) { //size is 1 when it is a leaf
            SegmentTreeElementaryInterval leftInterval = v.getLeft().getInterval();
            if (queryLine.getX() <= leftInterval.getX2() && queryLine.getX() >= leftInterval.getX1()) {
                query(v.getLeft(),queryLine);
            } else {
                query(v.getRight(),queryLine);
            }
        }
    }

    public List<HorizontalLineSegment> querySegmentTree(QueryLineSegment queryLine) {
        res = new LinkedList<>();
        query(rootNode, queryLine);
        return res;
    }

    @Override
    public String toString() {
        return getString(rootNode);
    }

    private String getString(SegmentTreeNode node) {

        String left = "";
        String right = "";
        String segments = "";
        String interval = node.getInterval().toString();
        for (HorizontalLineSegment seg: node.getI()) {
            segments+=seg;
        }
        if (node.getLeft() != null) {
            left = getString(node.getLeft());
        }
        if (node.getRight() != null) {
            right = getString(node.getRight());
        }
        return "("+ interval + segments + ";" + left+";"+ right +")";
    }

}
