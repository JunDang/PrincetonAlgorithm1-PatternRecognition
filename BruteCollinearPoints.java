import java.util.ArrayList;
import java.util.Arrays;
//import edu.princeton.cs.algs4.StdDraw;
//import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.In;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSegments = new ArrayList<LineSegment>();    
    public BruteCollinearPoints(Point[] points) {
        
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
        Point[] poi = new Point[4];
        for (int i = 0; i < points.length; i++)
        {
           for (int j = i + 1; j < points.length; j++)
           {
              for (int k = j + 1; k < points.length; k++)
              {
                 for (int m = k + 1; m < points.length; m++)
                 {
                       if ((points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]))
                               && (points[i].slopeTo(points[j]) == points[i].slopeTo(points[m])))
                       {
                          poi[0] = points[i];
                          poi[1] = points[j];
                          poi[2] = points[k];
                          poi[3] = points[m];
                          Arrays.sort(poi, 0, 4);
                            LineSegment lineSegment = new LineSegment(poi[0], poi[3]);
                            //System.out.println(lineSegment);
                            lineSegments.add(lineSegment); 
                       }
                    }
                }
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
    /*
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
           StdOut.println(segment);
            segment.draw();
        }
    }*/
}
