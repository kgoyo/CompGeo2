package kdTree;

import common.HorizontalLineSegment;
import common.QuickSelect;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KDTreeNode {

    private boolean isLeaf = false;
    private HorizontalLineSegment leafSegment; //only valid on leaves
    private double l; //only valid on nodes, might need to have some sense of axis
    private KDTreeNode vleft;
    private KDTreeNode vright;

    public KDTreeNode(List<HorizontalLineSegment> segments, int depth) {
        if (segments.size()==1) {
            isLeaf = true;
            leafSegment = segments.get(0);
        } else {
            isLeaf = false;
            List<Double> values = new ArrayList<>();
            List<HorizontalLineSegment> p1 = new ArrayList<>();
            List<HorizontalLineSegment> p2 = new ArrayList<>();
            switch (depth % 3) {
                case 0:
                    values.addAll(segments.stream().map(HorizontalLineSegment::getX1).collect(Collectors.toList())); //pretty cool thing I totally wrote on my own
                    l = QuickSelect.findMedian(values,0,values.size()-1);
                    for (HorizontalLineSegment seg: segments) {
                        if (seg.getX1() < l) {
                            p1.add(seg);
                        } else {
                            p2.add(seg);
                        }
                    }
                    break;
                case 1:
                    values.addAll(segments.stream().map(HorizontalLineSegment::getX2).collect(Collectors.toList()));
                    l = QuickSelect.findMedian(values,0,values.size()-1);
                    for (HorizontalLineSegment seg: segments) {
                        if (seg.getX2() < l) {
                            p1.add(seg);
                        } else {
                            p2.add(seg);
                        }
                    }
                    break;
                case 2:
                    values.addAll(segments.stream().map(HorizontalLineSegment::getY).collect(Collectors.toList()));
                    l = QuickSelect.findMedian(values,0,values.size()-1);
                    for (HorizontalLineSegment seg: segments) {
                        if (seg.getY() < l) {
                            p1.add(seg);
                        } else {
                            p2.add(seg);
                        }
                    }
                    break;
                default:
                    //never occurs
                    break;
            }
            vleft = new KDTreeNode(p1, depth+1);
            vright = new KDTreeNode(p2, depth+1);
        }
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public HorizontalLineSegment getLeafSegment() {
        return leafSegment;
    }

    public double getL() {
        return l;
    }

    public KDTreeNode getVleft() {
        return vleft;
    }

    public KDTreeNode getVright() {
        return vright;
    }
}
