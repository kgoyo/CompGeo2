import common.HorizontalLineSegment;
import common.QueryLineSegment;
import org.junit.Before;
import org.junit.Test;
import rangeTree.RangeTree1D;
import rangeTree.RangeTree2D;
import rangeTree.RangeTree3D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kgoyo on 20-12-2016.
 */
public class RangeTreeTest {

    private RangeTree1D tree1;
    private RangeTree2D tree2;
    private RangeTree3D tree3;

    @Before
    public void setup() {
        List<HorizontalLineSegment> input = new ArrayList<>();
        HorizontalLineSegment l1 = new HorizontalLineSegment(1,3,0);
        HorizontalLineSegment l2 = new HorizontalLineSegment(1.5,9,1);//
        HorizontalLineSegment l3 = new HorizontalLineSegment(1.1,2,2);
        HorizontalLineSegment l4 = new HorizontalLineSegment(4,7,3);//
        HorizontalLineSegment l5 = new HorizontalLineSegment(2.5,2.6,4);
        HorizontalLineSegment l6 = new HorizontalLineSegment(8,10,5);
        HorizontalLineSegment l7 = new HorizontalLineSegment(4.5,7.5,6);//
        HorizontalLineSegment l8 = new HorizontalLineSegment(6,7.6,7);
        HorizontalLineSegment l9 = new HorizontalLineSegment(11,12,8);
        input.add(l1);
        input.add(l2);
        input.add(l3);
        input.add(l4);
        input.add(l5);
        input.add(l6);
        input.add(l7);
        input.add(l8);
        input.add(l9);
        tree1 = new RangeTree1D(input);
        tree2 = new RangeTree2D(input);
        tree3 = new RangeTree3D(input);
    }

    @Test
    public void testTree1() {
        List<HorizontalLineSegment> out = tree1.rangeQuery1D(Double.MIN_VALUE, 4.5);
        for (HorizontalLineSegment seg: out) {
            System.out.println(seg);
        }
    }

    @Test
    public void testTree2() {
        List<HorizontalLineSegment> out = tree2.rangeQuery2D(Double.MIN_VALUE,4.5,4.5,Double.MAX_VALUE);
        for (HorizontalLineSegment seg: out) {
            System.out.println(seg);
        }
    }

    @Test
    public void testTree3() {
        QueryLineSegment queryLine = new QueryLineSegment(4.5,0,6);
        List<HorizontalLineSegment> out = tree3.rangeQuery3D(queryLine);
        for (HorizontalLineSegment seg: out) {
            System.out.println(seg);
        }
    }
}
