package ru.vsu.cs.shape;

import lombok.Getter;
import ru.vsu.cs.DoubleComparator;
import ru.vsu.cs.exceptions.JGeometryException;

@Getter
public class Line{
    private Point p1;
    private Point p2;

    public Line(Point p1, Point p2) throws JGeometryException {
        if (p1.equals(p2)) {
            throw new JGeometryException("Entered same points");
        }
        this.p1 = p1;
        this.p2 = p2;
    }

    public Line(int x1, int y1, int x2, int y2) {
        if (DoubleComparator.isEqual(x1,x2) && DoubleComparator.isEqual(y1,y2)) {
            return;
        }
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
    }

    public boolean contains(double x, double y) {
        return (x - p1.getX()) * (p2.getY() - p1.getY()) - (p2.getX() - p1.getX()) * (y - p1.getY()) == 0;
    }
}
