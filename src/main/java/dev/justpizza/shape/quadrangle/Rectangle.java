package dev.justpizza.shape.quadrangle;

import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.ellipse.Circle;
import dev.justpizza.utils.Utils;

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
            throw new IllegalShapeException(paramError("Rectangle"));
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

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public double getPerimeter() {
        return 2 * (sideA + sideB);
    }

    @Override
    protected Map<String, Object> getProperties() {
        return Utils.mergeProperties(
                Map.of(
                        "Side A", getSideA(),
                        "Side B", getSideB(),
                        "Diagonal", getDiagonal()
                ),
                super.getProperties()
        );
    }

    @Override
    public Circle createCircumcircle() {
        return Circle.fromRadius(getDiagonal() / 2);
    }

    @Override
    public Shape doubleArea() {
        return Rectangle.fromSides(getSideA()*Math.sqrt(2), getSideB()*Math.sqrt(2));
    }
}