package ru.vsu.cs.shape;

import lombok.Getter;
import ru.vsu.cs.DoubleComparator;

@Getter
public class Segment{
    private final double ebs = 0.000001;
    private Point p1;
    private Point p2;

    public Segment(Point p1, Point p2) {
        if (p1.equals(p2)) {
            return;
        }
        this.p1 = p1;
        this.p2 = p2;
    }

    public Segment(int x1, int y1, int x2, int y2) {
        if (DoubleComparator.isEqual(x1,x2) && DoubleComparator.isEqual(y1,y2)) {
            return;
        }
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
    }

    public double length() {
        return Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) +
                (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()));
    }

    public boolean contains(double x, double y) {
        double k, c;
        if (p1.getX() - p2.getX() <= ebs) {
            return (x - p1.getX() <= ebs && p1.getY() >= Math.min(p1.getY(), p2.getY()) &&
                    p1.getX() <= Math.max(p1.getY(), p2.getY()));
        }
        k = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
        c = p1.getY() - k * p1.getX();

        return y == x * k + c;
    }


}
