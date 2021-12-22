package ru.vsu.cs.shape;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Getter
public class Figure implements Shape {
    private List<Point> listOfPoints;
    private List<Segment> segments;

    public Figure(List<Point> list) {
        if (new HashSet<>(list).size() == list.size() && list.size() > 2) {
            listOfPoints = List.copyOf(list);
            segments = new ArrayList<>();
            for (int i = 1; i < listOfPoints.size(); i++) {
                segments.add(new Segment(listOfPoints.get(i - 1), listOfPoints.get(i)));
            }
            Point first = listOfPoints.get(0);
            Point last = listOfPoints.get(listOfPoints.size() - 1);
            segments.add(new Segment(last, first));
        }
        ;
    }

    @Override
    public boolean contains(double x, double y) {
        boolean result = false;
        int j = listOfPoints.size() - 1;
        for (int i = 0; i < listOfPoints.size(); i++) {
            if ((listOfPoints.get(i).getY() > y) != (listOfPoints.get(j).getY() > y) &&
                    (x < listOfPoints.get(i).getX() + (listOfPoints.get(j).getX() - listOfPoints.get(i).getX()) *
                            (y - listOfPoints.get(i).getY()) / (listOfPoints.get(j).getY() - listOfPoints.get(i).getY()))) {
                result = !result;
            }
            j = i;
        }
        return result;
    }
    public double perimeter(){
        double perimeter = 0;
        for (Segment s : segments) {
            perimeter += s.length();
        }
        return perimeter;
    }
    public double area(){
        double x = 0.;
        double y = 0.;
        for (int index = 0; index < listOfPoints.size()-1; index++) {
            x += this.listOfPoints.get(index).getX() * this.listOfPoints.get(index+ 1).getY();
            y += this.listOfPoints.get(index).getY() * this.listOfPoints.get(index+ 1).getX();
        }
        return Math.abs((x - y) / 2);
    }
}
