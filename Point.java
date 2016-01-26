import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void draw() {
        StdDraw.point(x, y);
    }
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    public double slopeTo(Point that) {
         if ((this.y == that.y) && (this.x == that.x))
         {
            return Double.NEGATIVE_INFINITY;
         }
         if (this.x == that.x)
         {
            return Double.POSITIVE_INFINITY;
         }
         if (this.y == that.y)
         {
            return 0.0;
         }

         return (that.y - this.y)*1.0/(that.x - this.x);
        
    }
    private class OrderofSlope implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double slope1 = slopeTo(p1);
            double slope2 = slopeTo(p2);
            if (slope1 < slope2) {
                return -1;
            }
            if (slope1 > slope2) {
                return 1;
            }
            return 0;
        }
    }
    public Comparator<Point> slopeOrder() {
        return new OrderofSlope();
    }
    @Override
    public int compareTo(Point o) {
        if (y > o.y) {
            return 1;
        }
        if (y < o.y) {
            return -1;
        }
        if (x > o.x) {
            return 1;
        } 
        if (x < o.x) {
            return -1;
        }
        return 0;
    }

}






