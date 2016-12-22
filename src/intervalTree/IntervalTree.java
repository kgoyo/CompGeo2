package intervalTree;

import common.HorizontalLineSegment;
import common.QueryLineSegment;
import common.QuickSelect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kgoyo on 08-12-2016.
 */
public class IntervalTree {

    private IntervalTreeNode rootNode;
    private ArrayList<HorizontalLineSegment> res;

    public IntervalTree(List<HorizontalLineSegment> segments) {
        rootNode = new IntervalTreeNode(segments);
    }

    public List<HorizontalLineSegment> querySegmentTree(QueryLineSegment queryLine) {
        res = new ArrayList<>();
        query(rootNode, queryLine);
        return res;
    }

    private void query(IntervalTreeNode v, QueryLineSegment queryLine) {
        if (!v.isLeaf()) {

            if ( queryLine.getX() < v.getXmid()) {
                if (v == rootNode) {
                    System.out.println("hey1");
                }
                res.addAll(v.getLleft().queryPrioSearchTree(queryLine));
                if (v.getLc() != null) {
                    query(v.getLc(), queryLine);
                }
            } else {
                if (v == rootNode) {
                    System.out.println("hey2");
                }
                res.addAll(v.getLright().queryPrioSearchTree(queryLine));
                if (v.getRc() != null) {
                    query(v.getRc(), queryLine);
                }
            }
        }
    }

    @Override
    public String toString() {return getString(rootNode);}

    private String getString(IntervalTreeNode node) {

        String left = "";
        String right = "";
        if (node.getLc() != null) {
            left = getString(node.getLc());
        }
        if (node.getRc() != null) {
            right = getString(node.getRc());
        }
        return "(xmid: "+ node.getXmid() + " lp: " + node.getLleft() + " rp: " + node.getLright() + ";" + left+";"+ right +")";
    }
}
