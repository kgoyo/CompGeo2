import common.HorizontalLineSegment;
import common.QueryLineSegment;
import org.junit.Before;
import org.junit.Test;
import prioritySearchTree.PrioritySearchTree;
import segmentTree.SegmentTree;

import java.util.ArrayList;
import java.util.List;

public class PrioritySearchTreeTest {
    private PrioritySearchTree tree;

    @Before
    public void setup() {
        List<HorizontalLineSegment> input = new ArrayList<>();
        HorizontalLineSegment l1 = new HorizontalLineSegment(1,3,1);
        HorizontalLineSegment l2 = new HorizontalLineSegment(1.5,9,7);
        HorizontalLineSegment l3 = new HorizontalLineSegment(1.1,2,3);
        HorizontalLineSegment l4 = new HorizontalLineSegment(4,7,2);
        HorizontalLineSegment l5 = new HorizontalLineSegment(2.5,2.6,4);
        HorizontalLineSegment l6 = new HorizontalLineSegment(8,10,5);
        HorizontalLineSegment l7 = new HorizontalLineSegment(4.5,7.5,3.5);
        HorizontalLineSegment l8 = new HorizontalLineSegment(6,7.6,8);
        HorizontalLineSegment l9 = new HorizontalLineSegment(11,12,9);
        input.add(l1);
        input.add(l2);
        input.add(l3);
        input.add(l4);
        input.add(l5);
        input.add(l6);
        input.add(l7);
        input.add(l8);
        input.add(l9);
        tree = new PrioritySearchTree(input);
        System.out.println(tree);
    }

    @Test
    public void someTest() {

        QueryLineSegment queryLine = new QueryLineSegment(4.51,2.2,10);
        List<HorizontalLineSegment> out = tree.queryPrioSearchTree(queryLine);
        for (HorizontalLineSegment seg: out) {
            System.out.println(seg);
        }
    }
}
