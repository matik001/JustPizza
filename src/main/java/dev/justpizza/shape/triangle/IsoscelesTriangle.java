package dev.justpizza.shape.triangle;

import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.utils.Utils;

import java.util.Map;

public class IsoscelesTriangle extends Triangle {
    private final double base;
    private final double height;
    private final double side;

    public IsoscelesTriangle(double base, double side, double height) throws IllegalShapeException {
        super(base, side, side);
        this.side = side;
        this.base = base;
        this.height = height;
    }

    public static Triangle fromBaseHeight(double base, double height) throws IllegalShapeException {
        double side = Math.sqrt(Math.pow(base / 2, 2) + Math.pow(height, 2));
        if (Utils.areClose(base, side)) {
            return EquilateralTriangle.fromSide((base + side) / 2);
        }
        return new IsoscelesTriangle(base, side, height);
    }

    public static Triangle fromBaseSide(double base, double side) throws IllegalShapeException {
        if (2 * side <= base) {
            throw new IllegalShapeException(paramError("IsoscelesTriangle"));
        }
        if (Utils.areClose(base, side)) {
            return EquilateralTriangle.fromSide((base + side) / 2);
        }
        double height = Math.sqrt(Math.pow(side, 2) - Math.pow(base / 2, 2));
        return new IsoscelesTriangle(base, side, height);
    }

    public static Triangle fromBaseArea(double base, double area) throws IllegalShapeException {
        double height = 2 * area / base;
        return IsoscelesTriangle.fromBaseHeight(base, height);
    }

    public static Triangle fromHeightArea(double height, double area) throws IllegalShapeException {
        double base = 2 * area / height;
        return IsoscelesTriangle.fromBaseHeight(base, height);
    }

    public static Triangle fromHeightSide(double height, double side) throws IllegalShapeException {
        if (side <= height) {
            throw new IllegalShapeException(paramError("IsoscelesTriangle"));
        }
        double base = Math.sqrt(Math.pow(side, 2) - Math.pow(height, 2)) * 2;
        return IsoscelesTriangle.fromBaseHeight(base, height);
    }

    public static Triangle fromSideArea(double side, double area) throws IllegalShapeException {
        // height^4 - height^2 * side^2 + area^2 = 0
        double delta = Math.pow(side, 4) - 4 * Math.pow(area, 2);
        if (delta < 0) {
            throw new IllegalShapeException(paramError("IsoscelesTriangle"));
        }
        double height = Math.sqrt((Math.pow(side, 2) + Math.sqrt(delta)) / 2);
        return IsoscelesTriangle.fromHeightSide(height, side);
    }

    public double getBase() {
        return base;
    }

    public double getSide() {
        return side;
    }

    public double getHeight() {
        return height;
    }

    @Override
    protected Map<String, Object> getProperties() {
        return Utils.mergeProperties(
                Map.of(
                        "Base", getBase(),
                        "Side", getSide(),
                        "Height", getHeight()
                ),
                super.getShapeProperties()
        );
    }

    @Override
    public Shape doubleArea() throws IllegalShapeException {
        return IsoscelesTriangle.fromBaseHeight(getBase() * Math.sqrt(2), getHeight() * Math.sqrt(2));
    }
}