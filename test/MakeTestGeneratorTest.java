import common.HorizontalLineSegment;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Created by Root on 17-12-2016.
 */
public class MakeTestGeneratorTest {

    public ArrayList<HorizontalLineSegment> readSegmentsFromFile(File f){
        List<String> lines = null;
        ArrayList<HorizontalLineSegment> res = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(f.getAbsolutePath()), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String line : lines){
           String[] split = Pattern.compile("[([)[,[;]]]]").split(line);
            res.add(new HorizontalLineSegment(new Double(split[1]), new Double(split[2]),new Double(split[3])));
        }
        return res;
    }

    public File makeTestFile(int n, int rectMax){

        ArrayList<HorizontalLineSegment> segments = new ArrayList<>();
        for(int i = 0; i < n; i++){
            double x1 = randomWithinRange(1,rectMax);
            double x2 = randomWithinRange(1,rectMax);
            double y = randomWithinRange(1,rectMax);

            if ( x2 < x1) {
                double temp;
                temp = x2;
                x2 = x1;
                x1 = temp;
            }

            segments.add(new HorizontalLineSegment(x1,x2,y));
        }

        List<String> lines = new ArrayList<String>();
        for (HorizontalLineSegment segment : segments){
            lines.add(segment.toString());
        }

        Path file = Paths.get("input\\"+"InputSet-"+n+"-"+rectMax+".input");
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.toFile();
    }

    @Test
    public void teststring(){
        String test = "(68.63091187342864,81.86725489184154;95.40306913686813)";
        String[] split = Pattern.compile("[([)[,[;]]]]").split(test);
        System.out.println(split[1]+"$"+split[2]+"$"+split[3]);
    }

    @Test
    public void fileReadTest() {
        ArrayList<HorizontalLineSegment> list = new ArrayList<>();
        list.addAll(readSegmentsFromFile(makeTestFile(200,400)));
        list.forEach(System.out::println);
    }

    @Test
    public void printInputFiles() throws IOException {
        System.out.println(makeTestFile(10,100).getName());
        //Files in the /out path
        //Files.walk(Paths.get(this.getClass().getClassLoader().getResource("").getPath())).filter(Files::isRegularFile).forEach(PrintFiles::printFiles);
        System.out.println(Paths.get(new File("").getAbsolutePath()+"\\input"));
        Files.walk(Paths.get(new File(".").getAbsolutePath()+"\\input"),0).forEach(PrintFiles::printFiles);

    }
    static class PrintFiles
    {

        static void printFiles(Path path)
        {
            File f = path.toAbsolutePath().toFile();
            List<String> filePaths = Arrays.asList(f.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if(name.endsWith(".input")){
                        return true;
                    } else {
                        return false;
                    }
                }
            }));

            if (!filePaths.isEmpty())
            {
                for(String filename : filePaths){
                    System.out.println(filename);
                }
            }
        }
    }

    private double randomWithinRange(double min, double max)
    {
        double range = (max - min);
        return (Math.random() * range) + min;
    }
}
