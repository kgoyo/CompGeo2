package intervalTree;

import common.HorizontalLineSegment;
import common.QuickSelect;
import prioritySearchTree.PrioritySearchTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kgoyo on 08-12-2016.
 */
public class IntervalTreeNode {


    private double xmid;
    private IntervalTreeNode lc;
    private  IntervalTreeNode rc;
    private boolean isLeaf = false;
    private PrioritySearchTree lleft;
    private PrioritySearchTree lright;


    public IntervalTreeNode(List<HorizontalLineSegment> segments) {
        if (segments.size() == 0) {
            isLeaf = true;
        } else {
            xmid = findMedianEndpoint(segments);
            List<HorizontalLineSegment> ileft = new ArrayList<>();
            List<HorizontalLineSegment> iright = new ArrayList<>();
            List<HorizontalLineSegment> imid = new ArrayList<>();
            for (HorizontalLineSegment seg: segments) {
                if (seg.getX1() <= xmid && seg.getX2() >= xmid) {
                    imid.add(seg);
                } else if (seg.getX2() < xmid) {
                    ileft.add(seg);
                } else if (seg.getX1() > xmid) {
                    iright.add(seg);
                }
            }
            lleft = new PrioritySearchTree(imid, true);
            lright = new PrioritySearchTree(imid, false);
            lc = new IntervalTreeNode(ileft);
            rc = new IntervalTreeNode(iright);
        }
    }

    public PrioritySearchTree getLleft() {
        return lleft;
    }

    public PrioritySearchTree getLright() {
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
        return endpoints.get(QuickSelect.findMedian(endpoints,0,endpoints.size()-1));
    }
}
