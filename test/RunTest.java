import common.HorizontalLineSegment;
import common.QueryLineSegment;
import intervalTree.IntervalTree;
import kdTree.KDTree;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import rangeTree.RangeTree3D;
import segmentTree.SegmentTree;

import java.io.File;
import java.util.*;

/**
 * Created by Root on 21/12/2016.
 */
public class RunTest {

    private static File input;

    @BeforeClass
    public static void SetUp(){
        input = MakeTestGeneratorTest.makeTestFile(50000,1000);
    }

    @Test
    public void make3DRangeTree() {
        System.out.println("\n3DRangeTree:");
        ArrayList<HorizontalLineSegment> segments = new ArrayList<>();
        segments = MakeTestGeneratorTest.readSegmentsFromFile(input);
        long start = new Date().getTime();
        RangeTree3D tree3 = new RangeTree3D(segments);
        System.out.println("Build time 3DRangeTree: "+(new Date().getTime()-start));
        System.out.println("Memeory usage: "+((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1048576)+"MB");
        start = new Date().getTime();
        List<HorizontalLineSegment> lol = tree3.rangeQuery3D(new QueryLineSegment(500,250,750));
        System.out.println("Query time 3DRangeTree: "+(new Date().getTime()-start));
        System.out.println("Size: "+lol.size());
    }

    @Test
    public void makeKDTree() {
        System.out.println("\nKDTree:");
        ArrayList<HorizontalLineSegment> segments = new ArrayList<>();
        segments = MakeTestGeneratorTest.readSegmentsFromFile(input);
        long start = new Date().getTime();
        KDTree tree = new KDTree(segments);
        System.out.println("Build time KDTree: "+(new Date().getTime()-start));
        System.out.println("Memeory usage: "+((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1048576)+"MB");
        start = new Date().getTime();
        List<HorizontalLineSegment> lol = tree.searchKDTree(new QueryLineSegment(500,250,750));
        System.out.println("Query time KDTree: "+(new Date().getTime()-start));
        System.out.println("Size: "+lol.size());
    }

    @Test
    public void makeIntervalTree() {
        System.out.println("\nIntervalTree:");
        ArrayList<HorizontalLineSegment> segments = new ArrayList<>();
        segments = MakeTestGeneratorTest.readSegmentsFromFile(input);
        long start = new Date().getTime();
        IntervalTree tree = new IntervalTree(segments);
        System.out.println("Build time IntervalTree: "+(new Date().getTime()-start));
        System.out.println("Memeory usage: "+((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1048576)+"MB");
        start = new Date().getTime();
        List<HorizontalLineSegment> lol = tree.querySegmentTree(new QueryLineSegment(500,250,750));
        System.out.println("Query time IntervalTree: "+(new Date().getTime()-start));
        System.out.println("Size: "+lol.size());
        /*
        Collections.sort(lol);
        for (HorizontalLineSegment seg: lol) {
            System.out.println(seg);
        }
        System.out.println(tree);*/
    }

    @Test
    public void makeSegmentTree() {
        System.out.println("\nSegmentTree:");
        ArrayList<HorizontalLineSegment> segments = new ArrayList<>();
        segments = MakeTestGeneratorTest.readSegmentsFromFile(input);
        long start = new Date().getTime();
        SegmentTree tree = new SegmentTree(segments);
        System.out.println("Build time SegmentTree: "+(new Date().getTime()-start));
        System.out.println("Memeory usage: "+((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1048576)+"MB");
        start = new Date().getTime();
        List<HorizontalLineSegment> lol = tree.querySegmentTree(new QueryLineSegment(500,250,750));
        System.out.println("Query time SegmentTree: "+(new Date().getTime()-start));
        System.out.println("Size: "+lol.size());
        /*
        Collections.sort(lol);
        for (HorizontalLineSegment seg: lol) {
            System.out.println(seg);
        }*/
    }
}
