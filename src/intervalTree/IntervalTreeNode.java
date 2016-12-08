package intervalTree;

import common.HorizontalLineSegment;
import common.QuickSelect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kgoyo on 08-12-2016.
 */
public class IntervalTreeNode {

    private List<HorizontalLineSegment> lleft = new ArrayList<>();
    private List<HorizontalLineSegment> lright = new ArrayList<>();
    private double xmid;
    private IntervalTreeNode lc;
    private  IntervalTreeNode rc;
    private boolean isLeaf = false;

    public IntervalTreeNode(List<HorizontalLineSegment> segments) {
        if (segments.size() == 0) {
            isLeaf = true;
        } else {
            xmid = findMedianEndpoint(segments);
            List<HorizontalLineSegment> ileft = new ArrayList<>();
            List<HorizontalLineSegment> iright = new ArrayList<>();
            for (HorizontalLineSegment seg: segments) {
                if (seg.getX1() <= xmid && seg.getX2() >= xmid) {
                    lleft.add(seg);
                    lright.add(seg);
                }
                if (seg.getX2() < xmid) {
                    ileft.add(seg);
                }
                if (seg.getX1() > xmid) {
                    iright.add(seg);
                }
            }
            Collections.sort(lleft, new Comparator<HorizontalLineSegment>() {
                @Override
                public int compare(HorizontalLineSegment o1, HorizontalLineSegment o2) {
                    return ((Double) o1.getX1()).compareTo(o2.getX1());
                }
            });
            Collections.sort(lright, new Comparator<HorizontalLineSegment>() {
                @Override
                public int compare(HorizontalLineSegment o1, HorizontalLineSegment o2) {
                    return ((Double) o1.getX2()).compareTo(o2.getX2());
                }
            });
            if (segments.size() > 1) {
                lc = new IntervalTreeNode(ileft);
                rc = new IntervalTreeNode(iright);
            }
        }
    }

    public List<HorizontalLineSegment> getLleft() {
        return lleft;
    }

    public List<HorizontalLineSegment> getLright() {
        return lright;
    }

    public double getXmid() {
        return xmid;
    }

    public IntervalTreeNode getLc() {
        return lc;
    }

    public IntervalTreeNode getRc() {
        return rc;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    private double findMedianEndpoint(List<HorizontalLineSegment> segments) {
        ArrayList<Double> endpoints = new ArrayList<>();
        for (HorizontalLineSegment seg : segments) {
            endpoints.add(seg.getX1());
            endpoints.add(seg.getX2());
        }
        return QuickSelect.findMedian(endpoints,0,endpoints.size()-1);
    }
}
