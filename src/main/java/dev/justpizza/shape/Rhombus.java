package dev.justpizza.shape;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Rhombus extends Shape {
    private final double diagonalA;
    private final double diagonalB;

    public Rhombus(double diagonalA, double diagonalB) {
        this.diagonalA = diagonalA;
        this.diagonalB = diagonalB;
    }

    public static Shape fromDiagonals(double diagonalA, double diagonalB) {
        if (diagonalA == diagonalB) {
            return Square.fromDiagonal(diagonalA);
        }
        return new Rhombus(diagonalA, diagonalB);
    }

    public static Shape fromDiagonalAndArea(double diagonal, double area) {
        return Rhombus.fromDiagonals(diagonal, 2.0 * area / diagonal);
    }

    public static Shape fromSideAndArea(double side, double area) throws IllegalShapeException {
        double delta = 16 * (Math.pow(side, 4) - area * area);
        if (delta < 0) {
            throw new IllegalShapeException("Rhombus with this parameters does not exist");
        }
        double diagonalA = Math.sqrt((4 * side * side + Math.sqrt(delta)) / 2);
        double diagonalB = area * 2 / diagonalA;
        return Rhombus.fromDiagonals(diagonalA, diagonalB);
    }

    public static Shape fromSideAndDiagonal(double side, double diagonal) {
        return Rhombus.fromDiagonals(diagonal, 2 * Math.sqrt(side * side - (diagonal * diagonal / 4.0)));
    }

    private double getDiagonalA() {
        return diagonalA;
    }

    private double getDiagonalB() {
        return diagonalB;
    }

    private double getArea() {
        return diagonalA * diagonalB / 2.0;
    }

    private double getSide() {
        return Math.sqrt(diagonalA * diagonalA + diagonalB * diagonalB) / 2;
    }

    @Override
    protected Map<String, Object> getProperties() {
        return Map.of("Diagonal A", getDiagonalA(),
                "Diagonal B", getDiagonalB(),
                "Side", getSide(),
                "Area", getArea());
    }

    @Override
    protected String getShapeName() {
        return "Rhombus";
    }

    @Override
    public double calcArea() {
        return getArea();
    }
}
