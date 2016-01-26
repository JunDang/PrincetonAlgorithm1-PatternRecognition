import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegments = new ArrayList<LineSegment>();    
    private HashMap<String, HashSet<Double>> slopes = new HashMap<String, HashSet<Double>>();

    public FastCollinearPoints(Point[] points) {
        
        if (points == null) {
            throw new java.lang.NullPointerException();
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new java.lang.NullPointerException();
            }
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new java.lang.IllegalArgumentException();
                }
            }
        }
        for (int i = 0; i < points.length; i++) {
            Point originPoint = points[i];
            Point [] points2 = new Point[points.length];
            for (int j = 0; j < points2.length; j++) {
                points2[j] = points[j];
            }
            Arrays.sort(points2, 0, points2.length, originPoint.slopeOrder());
             
            int j = 0;
            while (j < points2.length) {
                int step = 1;
                while ((j + step < points2.length) 
                        && (originPoint.slopeTo(points2[j]) == originPoint.slopeTo(points2[j + step]))
                        ) {
                   step++;
                }
                if (step >= 3) {
                    Point[] findPoints = new Point[step + 1];
                    findPoints[0] = originPoint;
                    for (int m = 0; m < step; m++) {
                        findPoints[m + 1] = points2[j + m];
                    }
                    Arrays.sort(findPoints, 0, findPoints.length);
                    
                    Point highP = findPoints[findPoints.length - 1];
                    Point lowP = findPoints[0];        
                    double slope = lowP.slopeTo(highP);
                    String highPString = highP.toString();
                    if ((!slopes.containsKey(highPString)) || (!slopes.get(highPString).contains(slope))) {
                        LineSegment lineSegment = new LineSegment(lowP, highP);
                        lineSegments.add(lineSegment);
                        if (slopes.containsKey(highPString)) {
                            slopes.get(highPString).add(slope);
                        } else {
                            HashSet<Double> set = new HashSet<Double>();
                            set.add(slope);
                            slopes.put(highPString, set);
                        }
                    }
                }
                j = j + step;
            }
        }
    }
    public int numberOfSegments() {
        return lineSegments.size();
    }
        
    public LineSegment[] segments() {
        LineSegment[] LineSegment = new LineSegment[numberOfSegments()];
        for (int i = 0; i < numberOfSegments(); i++) {
            LineSegment[i] = lineSegments.get(i);
        }
        return LineSegment;
    }
    
    public static void main(String[] args) {

        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
         
        } 
    
        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        //StdOut.println(collinear.segments().length);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }
    
}
