package ru.vsu.cs;

import ru.vsu.cs.shape.Point;
import ru.vsu.cs.shape.Segment;

public class JGeometry {
    public static boolean checkIntersectionOfTwoLineSegments(Segment s1,
                                                             Segment s2){
        return checkIntersectionOfTwoLineSegments(s1.getP1(),s1.getP2(),s2.getP1(),s2.getP2());
    }
    //метод, проверяющий пересекаются ли 2 отрезка [p1, p2] и [p3, p4]
    public static boolean checkIntersectionOfTwoLineSegments(Point p1, Point p2,
                                                       Point p3, Point p4) {
        //сначала расставим точки по порядку, т.е. чтобы было p1.x <= p2.x
        if (p2.getX() < p1.getX()) {
            Point tmp = p1;
            p1 = p2;
            p2 = tmp;
        }
        if (p4.getX() < p3.getX()) {
            Point tmp = p3;
            p3 = p4;
            p4 = tmp;
        }
        //проверим существование потенциального интервала для точки пересечения отрезков
        if (p2.getX() < p3.getX()) {
            return false; //ибо у отрезков нету взаимной абсциссы
        }
        //если оба отрезка вертикальные
        if ((p1.getX()- p2.getX()== 0) && (p3.getX() - p4.getX() == 0)) {
            //если они лежат на одном X
            if (p1.getX() - p3.getX()<=0.000001) {
                //проверим пересекаются ли они, т.е. есть ли у них общий Y
                //для этого возьмём отрицание от случая, когда они НЕ пересекаются
                return !((Math.max(p1.getY(), p2.getY()) < Math.min(p3.getY(), p4.getY())) ||
                        (Math.min(p1.getY(), p2.getY()) > Math.max(p3.getY(), p4.getY())));
            }
            return false;
        }
        //найдём коэффициенты уравнений, содержащих отрезки
        //f1(x) = A1*x + b1 = y
        //f2(x) = A2*x + b2 = y
        //если первый отрезок вертикальный
        if (p1.getX() - p2.getX() == 0) {
            //найдём Xa, Ya - точки пересечения двух прямых
            double Xa = p1.getX();
            double A2 = (p3.getY() - p4.getY()) / (p3.getX() - p4.getX());
            double b2 = p3.getY() - A2 * p3.getX();
            double Ya = A2 * Xa + b2;
            return p3.getX() <= Xa && p4.getX() >= Xa && Math.min(p1.getY(), p2.getY()) <= Ya &&
                    Math.max(p1.getY(), p2.getY()) >= Ya;
        }
        //если второй отрезок вертикальный
        if (p3.getX() - p4.getX() == 0) {
            //найдём Xa, Ya - точки пересечения двух прямых
            double Xa = p3.getX();
            double A1 = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
            double b1 = p1.getY() - A1 * p1.getX();
            double Ya = A1 * Xa + b1;
            return p1.getX() <= Xa && p2.getX() >= Xa && Math.min(p3.getY(), p4.getY()) <= Ya &&
                    Math.max(p3.getY(), p4.getY()) >= Ya;
        }
        //оба отрезка невертикальные
        double A1 = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
        double A2 = (p3.getY() - p4.getY()) / (p3.getX() - p4.getX());
        double b1 = p1.getY() - A1 * p1.getX();
        double b2 = p3.getY() - A2 * p3.getX();
        if (A1 == A2) {
            return false; //отрезки параллельны
        }
        //Xa - абсцисса точки пересечения двух прямых
        double Xa = (b2 - b1) / (A1 - A2);
        return (!(Xa < Math.max(p1.getX(), p3.getX()))) && (!(Xa > Math.min(p2.getX(), p4.getX())));
        //точка Xa находится вне пересечения проекций отрезков на ось X
    }
    public static Point getTheIntersectionPoint(Segment s1,
                                          Segment s2){
        if(checkIntersectionOfTwoLineSegments(s1,s2)){
            double a1 = s1.getP2().getY()-s1.getP1().getY();
            double b1 = s1.getP1().getX()-s1.getP2().getX();;
            double c1 = a1 * s1.getP1().getX()+ b1 * s1.getP1().getY();

            double a2 = s2.getP2().getY() - s2.getP1().getY();
            double b2 = s2.getP1().getX() -s2.getP2().getX();
            double c2 = a2 * s2.getP1().getX() + b2 * s2.getP1().getY();

            double delta = a1 * b2 - a2 * b1;
            return new Point(((b2 * c1 - b1 * c2) / delta), ((a1 * c2 - a2 * c1) / delta));
        }
        return null;
    }

}
