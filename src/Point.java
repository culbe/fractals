public class Point {

    double X, Y;

    public Point() {
        X = Y = 0;
    }

    public Point(double x, double y) {
        X = x;
        Y = y;
    }

    public double distance(Point p){
        double dx = this.X-p.X;
        double dy = this.Y-p.Y;
        return Math.sqrt(dx*dx+dy*dy);
    }
}
