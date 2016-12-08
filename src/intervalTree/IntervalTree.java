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
                for (HorizontalLineSegment seg: v.getLleft()){
                    if (queryLine.doesIntersectX(seg)) {
                        res.add(seg);
                    } else {
                        break;
                    }
                }
                if (v.getLc() != null) {
                    query(v.getLc(), queryLine);
                }
            } else {
                for (HorizontalLineSegment seg: v.getLright()) {
                    if (queryLine.doesIntersectX(seg)) {
                        res.add(seg);
                    } else {
                        break;
                    }
                }
                if (v.getRc() != null) {
                    query(v.getRc(), queryLine);
                }
            }
        }
    }
}
