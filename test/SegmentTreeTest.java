import static org.junit.Assert.*;

import common.HorizontalLineSegment;
import common.QueryLineSegment;
import org.junit.*;
import segmentTree.SegmentTree;

import java.util.ArrayList;
import java.util.List;

public class SegmentTreeTest {

    private SegmentTree tree;

    @Before
    public void setup() {
        List<HorizontalLineSegment> input = new ArrayList<>();
        HorizontalLineSegment l1 = new HorizontalLineSegment(1,3,0);
        HorizontalLineSegment l2 = new HorizontalLineSegment(1.5,9,7);//not
        HorizontalLineSegment l3 = new HorizontalLineSegment(1.1,2,0);
        HorizontalLineSegment l4 = new HorizontalLineSegment(4,7,2);//+
        HorizontalLineSegment l5 = new HorizontalLineSegment(2.5,2.6,0);
        HorizontalLineSegment l6 = new HorizontalLineSegment(8,10,0);
        HorizontalLineSegment l7 = new HorizontalLineSegment(4.5,7.5,0);//+
        HorizontalLineSegment l8 = new HorizontalLineSegment(6,7.6,0);
        HorizontalLineSegment l9 = new HorizontalLineSegment(11,12,0);
        input.add(l1);
        input.add(l2);
        input.add(l3);
        input.add(l4);
        input.add(l5);
        input.add(l6);
        input.add(l7);
        input.add(l8);
        input.add(l9);
        tree = new SegmentTree(input);
        System.out.println(tree);
    }

    @Test
    public void someTest() {

        QueryLineSegment queryLine = new QueryLineSegment(4.51,6,8);
        List<HorizontalLineSegment> out = tree.querySegmentTree(queryLine);
        for (HorizontalLineSegment seg: out) {
            System.out.println(seg);
        }
        System.out.println(out.size());
    }
}
