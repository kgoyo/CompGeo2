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
        rootNode = new KDTreeNode(segments, 0);
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
            if (isFullyContained(node.getVleft(),R)) {
                reportSubTree(node.getVleft());
            } else {
                //if intersects R TODO
                search(node.getVleft(),R);
            }

            if (isFullyContained(node.getVright(),R)) {
                reportSubTree(node.getVright());
            } else {
                //if intersects R TODO
                search(node.getVright(),R);
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

    private boolean isFullyContained(KDTreeNode node, QueryLineSegment R) {
        return false; //TODO make proper implementation
    }
}
