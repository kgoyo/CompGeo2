package rangeTree;

import common.HorizontalLineSegment;

import java.util.List;

/**
 * Created by kgoyo on 15-12-2016.
 */
public class RangeTree2D {
    private RangeTree2DNode rootNode;

    public RangeTree2D(List<HorizontalLineSegment> segments) {
        rootNode = new RangeTree2DNode(segments);
    }
}
