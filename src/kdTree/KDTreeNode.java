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
    private KDTreeRegion region;

    public KDTreeNode(List<HorizontalLineSegment> segments, KDTreeRegion region, int depth) {
        this.region = region;
        if (segments.size()==1) {
            isLeaf = true;
            leafSegment = segments.get(0);
        } else {
            isLeaf = false;
            List<Double> values = new ArrayList<>();
            List<HorizontalLineSegment> p1 = new ArrayList<>();
            List<HorizontalLineSegment> p2 = new ArrayList<>();
            KDTreeRegion lRegion;
            KDTreeRegion rRegion;
            switch (depth % 3) {
                case 0:
                    values.addAll(segments.stream().map(HorizontalLineSegment::getX1).collect(Collectors.toList())); //X1->x
                    l = values.get(QuickSelect.findMedian(values,0,values.size()-1));
                    for (HorizontalLineSegment seg: segments) {
                        if (seg.getX1() < l) {
                            p1.add(seg);
                        } else {
                            p2.add(seg);
                        }
                    }
                    lRegion = new KDTreeRegion(region.getX1(), l,region.getY1(), region.getY2(), region.getZ1(), region.getZ2());
                    rRegion = new KDTreeRegion(l, region.getX2(),region.getY1(), region.getY2(), region.getZ1(), region.getZ2());
                    break;
                case 1:
                    values.addAll(segments.stream().map(HorizontalLineSegment::getX2).collect(Collectors.toList())); //X2->y
                    l = values.get(QuickSelect.findMedian(values,0,values.size()-1));
                    for (HorizontalLineSegment seg: segments) {
                        if (seg.getX2() < l) {
                            p1.add(seg);
                        } else {
                            p2.add(seg);
                        }
                    }
                    lRegion = new KDTreeRegion(region.getX1(), region.getX2(), region.getY1(), l, region.getZ1(), region.getZ2());
                    rRegion = new KDTreeRegion(region.getX1(), region.getX2(), l, region.getY2(), region.getZ1(), region.getZ2());
                    break;
                case 2:
                    values.addAll(segments.stream().map(HorizontalLineSegment::getY).collect(Collectors.toList())); //Y->z
                    l = values.get(QuickSelect.findMedian(values,0,values.size()-1));
                    for (HorizontalLineSegment seg: segments) {
                        if (seg.getY() < l) {
                            p1.add(seg);
                        } else {
                            p2.add(seg);
                        }
                    }
                    lRegion = new KDTreeRegion(region.getX1(), region.getX2(), region.getY1(), region.getY2(), region.getZ1(), l);
                    rRegion = new KDTreeRegion(region.getX1(), region.getX2(), region.getY1(), region.getY2(), l, region.getZ2());
                    break;
                default:
                    //never occurs, but is needed for java to stop complaining
                    lRegion = new KDTreeRegion(region.getX1(), region.getX2(), region.getY1(), region.getY2(), region.getZ1(), region.getZ2());
                    rRegion = new KDTreeRegion(region.getX1(), region.getX2(), region.getY1(), region.getY2(), region.getZ1(), region.getZ2());
                    break;
            }
            if (p1.size() > 0) {
                vleft = new KDTreeNode(p1, lRegion, depth + 1);
            }
            if (p2.size() > 0) {
                vright = new KDTreeNode(p2, rRegion, depth + 1);
            }
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

    public KDTreeRegion getRegion() {
        return region;
    }
}
