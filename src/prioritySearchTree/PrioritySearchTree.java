package prioritySearchTree;

import common.HorizontalLineSegment;
import common.QueryLineSegment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kgoyo on 11-12-2016.
 */
public class PrioritySearchTree {
    private final PrioritySearchTreeNode rootNode;
    private ArrayList<HorizontalLineSegment> res;

    public PrioritySearchTree(List<HorizontalLineSegment> segments) {
        rootNode = new PrioritySearchTreeNode(segments);
    }

    public List<HorizontalLineSegment> queryPrioSearchTree(QueryLineSegment queryLine) {
        res = new ArrayList<>();
        query(rootNode,queryLine);
        return res;
    }

    private void query(PrioritySearchTreeNode node, QueryLineSegment queryLine) {
        //TODO psudocode in book is pretty bad
    }

    private void reportInSubtree(PrioritySearchTreeNode node, double qx) {
        if (!node.isLeaf()) {
            if (node.getpMin().getX1() <= qx) {
                res.add(node.getpMin());
                reportInSubtree(node.getLc(), qx);
                reportInSubtree(node.getRc(), qx);
            }
        }
    }
}
