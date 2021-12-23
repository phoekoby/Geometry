package ru.vsu.cs;

public class DoubleComparator {
    public static final double EPS = 1e-7;
    public static boolean isEqual(double first, double second){
        return Math.abs(first-second)<EPS;
    }
}
