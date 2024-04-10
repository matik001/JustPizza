package dev.justpizza.shape;

import java.util.Map;

public class Rectangle extends Shape {
    private double sideA;
    private double sideB;
    private double diagonal;
    private double area;

    private Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.diagonal = Math.sqrt(sideA * sideA + sideB * sideB);
        this.area = sideA * sideB;
    }

    public static Shape fromSides(double sideA, double sideB) {
        if (sideA == sideB) return new Square(sideA);
        return new Rectangle(sideA, sideB);
    }

    public static Shape fromDiagonalAndSide(double diagonal, double side) {
        double otherSide = Math.sqrt(diagonal * diagonal - side * side);
        return Rectangle.fromSides(side, otherSide);
    }

    public static Shape fromAreaAndSide(double area, double side) {
        double otherSide = area / side;
        return Rectangle.fromSides(side, otherSide);
    }

    public static Shape fromDiagonalAndArea(double diagonal, double area) throws IllegalShapeException {
        double delta = Math.pow(diagonal, 4) - 4 * area * area;
        if (delta < 0) {
            throw new IllegalShapeException("Rectangle with this parameters does not exist");
        }
        double b = Math.sqrt((diagonal * diagonal - Math.sqrt(delta)) / 2);
        double a = area / b;
        return Rectangle.fromSides(a, b);
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public double getArea() {
        return area;
    }

    @Override
    protected Map<String, Object> getProperties() {
        return Map.of("Side A", getSideA(),
                "Side B", getSideB(),
                "Diagonal", getDiagonal(),
                "Area", getArea());
    }

    @Override
    protected String getShapeName() {
        return "Rectangle";
    }

    @Override
    public double calcArea() {
        return getArea();
    }

    @Override
    public Circle createCircumcircle() {
        return Circle.fromRadius(getDiagonal() / 2);
    }
}