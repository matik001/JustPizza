package dev.justpizza.shape.triangle;

import dev.justpizza.shape.IllegalShapeException;

import java.util.Map;

public class IsoscelesTriangle extends Triangle {
    private final double base;
    private final double height;

    public IsoscelesTriangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public static IsoscelesTriangle fromBaseHeight(double base, double height) {
        return new IsoscelesTriangle(base, height);
    }

    public static IsoscelesTriangle fromBaseSide(double base, double side) throws IllegalShapeException {
        if (2 * side < base) throw new IllegalShapeException("IsoscelesTriangle with this parameters does not exist");
        double height = Math.sqrt(Math.pow(side, 2) - Math.pow(base / 2, 2));
        return new IsoscelesTriangle(base, height);
    }

    public static IsoscelesTriangle fromBaseArea(double base, double area) {
        double height = 2 * area / base;
        return new IsoscelesTriangle(base, height);
    }

    public static IsoscelesTriangle fromHeightArea(double height, double area) {
        double base = 2 * area / height;
        return new IsoscelesTriangle(base, height);
    }

    public static IsoscelesTriangle fromHeightSide(double height, double side) throws IllegalShapeException {
        if (side < height) throw new IllegalShapeException("IsoscelesTriangle with this parameters does not exist");
        double base = Math.sqrt(Math.pow(side, 2) - Math.pow(height, 2)) * 2;
        return new IsoscelesTriangle(base, height);
    }

    public static IsoscelesTriangle fromSideArea(double side, double area) throws IllegalShapeException {
        // height^4 - height^2 * side^2 + area^2 = 0
        double delta = Math.pow(side, 4) - 4 * Math.pow(area, 2);
        if (delta < 0) throw new IllegalShapeException("IsoscelesTriangle with this parameters does not exist");
        double height = Math.sqrt((Math.pow(side, 2) + Math.sqrt(delta)) / 2);
        return IsoscelesTriangle.fromHeightSide(height, side);
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
        return getBase();
    }

    public double getBase() {
        return base;
    }

    public double getSide() {
        return Math.sqrt(Math.pow(base / 2, 2) + Math.pow(height, 2));
    }

    public double getHeight() {
        return height;
    }

    @Override
    protected Map<String, Object> getProperties() {
        return Map.of("Base", getBase(),
                "Side", getSide(),
                "Height", getHeight(),
                "Area", getArea());
    }

    @Override
    protected String getShapeName() {
        return "IsoscelesTriangle";
    }


    @Override
    public double getArea() {
        return getArea();
    }
}
