package dev.justpizza.shape.quadrangle;

import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.ellipse.Circle;
import dev.justpizza.utils.Utils;

import java.util.Map;

public class Rhombus extends Shape {
    private final double diagonalA;
    private final double diagonalB;

    public Rhombus(double diagonalA, double diagonalB) {
        if (diagonalA >= diagonalB) {
            this.diagonalA = diagonalA;
            this.diagonalB = diagonalB;
        } else {
            this.diagonalA = diagonalB;
            this.diagonalB = diagonalA;
        }
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
            throw new IllegalShapeException(paramError("Rhombus"));
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

    @Override
    public double getArea() {
        return diagonalA * diagonalB / 2.0;
    }

    @Override
    public double getPerimeter() {
        return 4 * getSide();
    }

    private double getSide() {
        return Math.sqrt(diagonalA * diagonalA + diagonalB * diagonalB) / 2;
    }

    @Override
    protected Map<String, Object> getProperties() {
        return Utils.mergeProperties(
                Map.of(
                        "Diagonal A", getDiagonalA(),
                        "Diagonal B", getDiagonalB(),
                        "Side", getSide()
                ),
                super.getProperties()
        );
    }

    @Override
    public Circle createCircumcircle() throws IllegalShapeException {
        throw new IllegalShapeException("Can't create circumcircle");
    }

    @Override
    public Shape doubleArea() {
        return Rhombus.fromDiagonals(getDiagonalA()*Math.sqrt(2), getDiagonalB()*Math.sqrt(2));
    }
}
