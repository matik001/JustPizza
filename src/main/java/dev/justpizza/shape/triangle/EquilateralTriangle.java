package dev.justpizza.shape.triangle;

import java.util.Map;

public class EquilateralTriangle extends Triangle {
    private final double side;

    public EquilateralTriangle(double side) {
        this.side = side;
    }

    public static EquilateralTriangle fromSide(double side) {
        return new EquilateralTriangle(side);
    }

    public static EquilateralTriangle fromHeight(double height) {
        return new EquilateralTriangle(height * 2 / Math.sqrt(3.0));
    }

    public static EquilateralTriangle fromArea(double area) {
        return new EquilateralTriangle(Math.sqrt(4 * area / Math.sqrt(3)));
    }

    @Override
    public double getSideA() {
        return getSide();
    }

    @Override
    public double getSideB() {
        return getSide();
    }

    @Override
    public double getSideC() {
        return getSide();
    }

    public double getSide() {
        return side;
    }

    public double getHeight() {
        return side * Math.sqrt(3) / 2;
    }


    @Override
    protected Map<String, Object> getProperties() {
        return Map.of("Side", getSide(),
                "Height", getHeight(),
                "Area", getArea());
    }

    @Override
    protected String getShapeName() {
        return "EquilateralTriangle";
    }

    @Override
    public double getArea() {
        return getArea();
    }
}
