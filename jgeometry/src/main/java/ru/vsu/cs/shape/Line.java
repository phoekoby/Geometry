package ru.vsu.cs.shape;

import lombok.Getter;

@Getter
public class Line implements Shape {
    private Point p1;
    private Point p2;

    public Line(Point p1, Point p2) {
        if(p1.getX()-p2.getX()<=0.000001&&p1.getY()-p2.getY()<=0.0000001){
            return;
        }
        this.p1 = p1;
        this.p2 = p2;
    }

    public Line(int x1, int y1, int x2, int y2) {
        if(x1-x2<=0.00001&&y1-y2<=0.000001){
            return;
        }
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
    }

    @Override
    public boolean contains(double x, double y) {
        return (x - p1.getX()) * (p2.getY() - p1.getY()) - (p2.getX() - p1.getX()) * (y - p1.getY()) == 0;
    }

}
