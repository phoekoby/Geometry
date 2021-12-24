package ru.vsu.cs.shape;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.vsu.cs.DoubleComparator;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor

public class Point{
    private double x, y;

    public double distanceTo(double x2, double y2) {
        return Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        return DoubleComparator.isEqual(((Point) obj).getX(), this.x) &&
                DoubleComparator.isEqual(((Point) obj).getY(), this.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ";" + y + ")";
    }
}
