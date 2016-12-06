package segmentTree;

import common.HorizontalLineSegment;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kgoyo on 01-12-2016.
 */
public class SegmentTreeNode {

    private SegmentTreeNode left;
    private SegmentTreeNode right;
    private LinkedList<HorizontalLineSegment> I = new LinkedList<>();
    private SegmentTreeElementaryInterval interval;

    public SegmentTreeNode getLeft() {
        return left;
    }

    public SegmentTreeNode getRight() {
        return right;
    }

    public LinkedList<HorizontalLineSegment> getI() {
        return I;
    }

    public SegmentTreeElementaryInterval getInterval() {
        return interval;
    }

    public SegmentTreeNode(List<SegmentTreeElementaryInterval> list) {
        /*if (list.size() == 0) {
            interval = null;
        } else*/ if (list.size() == 1) {
            interval = list.get(0);
        } else {
            int middle = (int) Math.floor(list.size()/2);

//            System.out.println(0+"->"+middle+";"+middle+"->"+(list.size()));

            List<SegmentTreeElementaryInterval> leftIntervals = list.subList(0,middle);
            List<SegmentTreeElementaryInterval> rightIntervals = list.subList(middle,list.size());
            left = new SegmentTreeNode(leftIntervals);
            right = new SegmentTreeNode(rightIntervals);
            interval = new SegmentTreeElementaryInterval(
                    left.getInterval().getX1(),
                    right.getInterval().getX2());
        }

    }

    public void store (HorizontalLineSegment segment) {
        I.add(segment);
    }
}
