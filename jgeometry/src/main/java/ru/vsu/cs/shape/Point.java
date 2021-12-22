package ru.vsu.cs.shape;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Point implements Shape {
    private double x, y;

    @Override
    public boolean contains(double x, double y) {
        return x - this.x <= 0.000001 && y - this.y <= 0.000001;
    }
    public double distanceTo(double x2, double y2){
        return Math.sqrt((x-x2)*(x-x2)+(y-y2)*(y-y2));
    }
}
