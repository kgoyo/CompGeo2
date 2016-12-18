package rangeTree;

import common.HorizontalLineSegment;
import common.QuickSelect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kgoyo on 15-12-2016.
 */
public class RangeTree2DNode {
    private RangeTree1D assoc;
    private double xmid;
    private RangeTree2DNode lc;
    private RangeTree2DNode rc;


    public RangeTree2DNode(List<HorizontalLineSegment> segments) {
        assoc = new RangeTree1D(segments); //TODO like this????
        if (segments.size() == 1) {
            //create leaf??? with assoc
        } else {
            List<Double> values = new ArrayList<>();
            //FIXME dont know if X1 or something else
            values.addAll(segments.stream().map(HorizontalLineSegment::getX1).collect(Collectors.toList()));
            xmid = segments.get(QuickSelect.findMedian(values,0,segments.size()-1)).getX1();
            List<HorizontalLineSegment> pLeft = new ArrayList<>();
            List<HorizontalLineSegment> pRight = new ArrayList<>();
            for (HorizontalLineSegment seg: segments) {
                if (seg.getX1() <= xmid) {
                    pLeft.add(seg);
                } else {
                    pRight.add(seg);
                }
            }
            lc = new RangeTree2DNode(pLeft);
            rc = new RangeTree2DNode(pRight);
        }
    }
}
