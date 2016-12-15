package kdTree;

import common.HorizontalLineSegment;
import common.QueryLineSegment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kgoyo on 09-12-2016.
 */
public class KDTree {
    private final KDTreeNode rootNode;
    private List<HorizontalLineSegment> res;

    public KDTree (List<HorizontalLineSegment> segments) {
        //segments correspond to the point (x1,x2,y)
        KDTreeRegion rootRegion = new KDTreeRegion(Double.MIN_VALUE,Double.MAX_VALUE,Double.MIN_VALUE,Double.MAX_VALUE,Double.MIN_VALUE,Double.MAX_VALUE);
        rootNode = new KDTreeNode(segments, rootRegion, 0);
    }

    public List<HorizontalLineSegment> searchKDTree(QueryLineSegment R) {
        res = new ArrayList<HorizontalLineSegment>();
        search(rootNode,R);
        return res;
    }

    private void search(KDTreeNode node, QueryLineSegment R) {
        if (node.isLeaf()) {
            HorizontalLineSegment segment = node.getLeafSegment();
            if (R.isInRange3D(segment)) {
                res.add(segment);
            }
        } else {
            if (node.getVleft() != null) {
                if (isFullyContained(node.getVleft().getRegion(),R)) {
                    reportSubTree(node.getVleft());
                } else {
                    if (intersect(node.getVleft().getRegion(),R)) {
                        search(node.getVleft(), R);
                    }
                }
            }
            if (node.getVright() != null) {
                if (isFullyContained(node.getVright().getRegion(),R)) {
                    reportSubTree(node.getVright());
                } else {
                    if (intersect(node.getVright().getRegion(),R)) {
                        search(node.getVright(), R);
                    }
                }
            }
        }
    }

    private void reportSubTree(KDTreeNode node) {
        if (node.isLeaf()) {
            res.add(node.getLeafSegment());
        } else {
            reportSubTree(node.getVleft());
            reportSubTree(node.getVright());
        }
    }

    private boolean isFullyContained(KDTreeRegion region, QueryLineSegment R) {
        if (region.getX2() > R.getX()) {
            return false;
        }
        if (R.getX() > region.getY1()) {
            return false;
        }
        if (R.getY1() > region.getZ1() || region.getZ2() > R.getY2()) {
            return false;
        }
        return true;
    }

    private boolean intersect(KDTreeRegion region, QueryLineSegment R) {
        if (region.getX1() > R.getX()) {
            return false;
        }
        if (R.getX() > region.getY2()) {
            return false;
        }
        if (R.getY1() > region.getZ2() && region.getZ1() > R.getY2()) {
            return false;
        }
        return true;
    }
}
