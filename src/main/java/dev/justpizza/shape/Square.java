package dev.justpizza.shape;

import java.util.Map;

public class Square extends Shape {
    private final double side;

    public Square(double side) {
        this.side = side;
    }

    public static Square fromSide(double side) {
        return new Square(side);
    }

    public static Square fromDiagonal(double diagonal) {
        return new Square(diagonal / Math.sqrt(2));
    }

    public static Square fromArea(double area) {
        return new Square(Math.sqrt(area));
    }

    public double getSide() {
        return side;
    }

    public double getArea() {
        return side * side;
    }

    public double getDiagonal() {
        return side * Math.sqrt(2);
    }


    @Override
    protected Map<String, Object> getProperties() {
        return Map.of("Side", getSide(),
                "Diagonal", getDiagonal(),
                "Area", getArea());
    }

    @Override
    protected String getShapeName() {
        return "Square";
    }
    @Override
    public double calcArea() {
        return getArea();
    }
}
