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
    private boolean leftQuery;

    public PrioritySearchTree(List<HorizontalLineSegment> segments, boolean leftQuery) {
        this.leftQuery = leftQuery;
        rootNode = new PrioritySearchTreeNode(segments, leftQuery);
    }

    public List<HorizontalLineSegment> queryPrioSearchTree(QueryLineSegment queryLine) {
        res = new ArrayList<>();
        List<PrioritySearchTreeNode> nodes = new ArrayList<>();
        getNodesUntillvSplit(rootNode,queryLine, nodes);
        for (PrioritySearchTreeNode node: nodes) {
            reportIfInSearchPath(node,queryLine);
        }
        if (nodes.size() > 0) {
            PrioritySearchTreeNode vSplit = nodes.get(nodes.size() - 1);
            if (!vSplit.isLeaf()) {
                traverseSubtree(vSplit.getLc(), queryLine, true);
                traverseSubtree(vSplit.getRc(), queryLine, false);
            }
        }
        return res;
    }

    private void getNodesUntillvSplit(PrioritySearchTreeNode node, QueryLineSegment queryLine, List<PrioritySearchTreeNode> nodes) {
        //check if querylines yvalues are on the same side of node.ymid

        if (!node.isLeaf()) {
            nodes.add(node);
            if (node.getyMid() < queryLine.getY1() && node.getyMid() < queryLine.getY2()) {
                getNodesUntillvSplit(node.getRc(), queryLine, nodes);
            } else if (node.getyMid() >= queryLine.getY1() && node.getyMid() >= queryLine.getY2()) {
                getNodesUntillvSplit(node.getLc(), queryLine, nodes);
            }
        }
    }

    private void reportIfInSearchPath(PrioritySearchTreeNode node, QueryLineSegment queryLine) {
        if (!node.isLeaf()) {
            if (leftQuery) {
                if (node.getpMin().getX1() <= queryLine.getX() &&
                        node.getpMin().getY() >= queryLine.getY1() &&
                        node.getpMin().getY() <= queryLine.getY2()) {
                    res.add(node.getpMin());
                }
            } else {
                if (node.getpMin().getX2() >= queryLine.getX() &&
                        node.getpMin().getY() >= queryLine.getY1() &&
                        node.getpMin().getY() <= queryLine.getY2()) {
                    res.add(node.getpMin());
                }
            }
        }
    }

    private void traverseSubtree(PrioritySearchTreeNode node, QueryLineSegment queryLine, boolean isLeft) {
        if (!node.isLeaf()) {
            reportIfInSearchPath(node,queryLine);
            if (isLeft) {
                if (node.getyMid() >= queryLine.getY1()) {
                    traverseSubtree(node.getLc(), queryLine, isLeft);
                    reportInSubtree(node.getRc(), queryLine.getX());
                } else {
                    traverseSubtree(node.getRc(), queryLine, isLeft);
                }
            } else {
                if (node.getyMid() <= queryLine.getY2()) {
                    traverseSubtree(node.getRc(), queryLine, isLeft);
                    reportInSubtree(node.getLc(), queryLine.getX());
                } else {
                    traverseSubtree(node.getLc(), queryLine, isLeft);
                }
            }
        }
    }

    private void reportInSubtree(PrioritySearchTreeNode node, double qx) {
        if (!node.isLeaf()) {
            if (leftQuery) {
                if (node.getpMin().getX1() <= qx) {
                    res.add(node.getpMin());
                    reportInSubtree(node.getLc(), qx);
                    reportInSubtree(node.getRc(), qx);
                }

            } else {
                if (node.getpMin().getX2() >= qx) {
                    res.add(node.getpMin());
                    reportInSubtree(node.getLc(), qx);
                    reportInSubtree(node.getRc(), qx);
                }
            }
        }
    }

    @Override
    public String toString() {
        return getString(rootNode);
    }

    private String getString(PrioritySearchTreeNode node) {

        String left = "";
        String right = "";
        if (!node.isLeaf()) {
            String segment = node.getpMin().toString();
            if (node.getLc() != null) {
                left = getString(node.getLc());
            }
            if (node.getRc() != null) {
                right = getString(node.getRc());
            }
            return "(" + segment + ";" + left + ";" + right + ")";
        } else {
            return "$";
        }
    }


}
