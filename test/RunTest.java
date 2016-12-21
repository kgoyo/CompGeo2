import common.HorizontalLineSegment;
import common.QueryLineSegment;
import org.junit.Test;
import rangeTree.RangeTree3D;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Root on 21/12/2016.
 */
public class RunTest {

    @Test
    public void makeRun(){
        File f = MakeTestGeneratorTest.makeTestFile(1000,1000);
        ArrayList<HorizontalLineSegment> segments = new ArrayList<>();
        long start = System.currentTimeMillis();
        RangeTree3D tree3 = new RangeTree3D(segments);
        System.out.println("Build time 3DRangeTree: "+(System.currentTimeMillis()-start));
        System.out.println("Memeory usage: "+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()));
        tree3.rangeQuery3D(new QueryLineSegment(500,250,750));
        System.out.println("Query time 3DRangeTree: "+(System.currentTimeMillis()-start));
    }
}
