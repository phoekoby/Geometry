package ru.vsu.cs.shape;

import lombok.Getter;

@Getter
public class Segment implements Shape {
    private Point p1;
    private Point p2;

    public Segment(Point p1, Point p2) {
        if(p1.getX()-p2.getX()<=0.000001&&p1.getY()-p2.getY()<=0.0000001){
            return;
        }
        this.p1 = p1;
        this.p2 = p2;
    }

    public Segment(int x1, int y1, int x2, int y2) {
        if(x1-x2<=0.00001&&y1-y2<=0.000001){
            return;
        }
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
    }

    public double length(){
        return Math.sqrt((p1.getX()-p2.getX())*(p1.getX()-p2.getX())+(p1.getY()-p2.getY())*(p1.getY()-p2.getY()));
    }
    @Override
    public boolean contains(double x, double y) {
        double k, c;
        if (p1.getX() - p2.getX() <= 0.00001) {
            return (x - p1.getX()<=0.00001 && p1.getY() >= Math.min(p1.getY(), p2.getY()) && p1.getX() <= Math.max(p1.getY(), p2.getY()));
        }
        k = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
        c = p1.getY() - k * p1.getX();

        return y == x * k + c;
    }


}
