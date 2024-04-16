package dev.justpizza.shape.triangle;

import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.utils.Utils;

import java.util.Map;

public class EquilateralTriangle extends Triangle {
    private final double side;

    public EquilateralTriangle(double side) throws IllegalShapeException {
        super(side, side, side);
        this.side = side;
    }

    public static EquilateralTriangle fromSide(double side) throws IllegalShapeException {
        return new EquilateralTriangle(side);
    }

    public static EquilateralTriangle fromHeight(double height) throws IllegalShapeException {
        return new EquilateralTriangle(height * 2 / Math.sqrt(3.0));
    }

    public static EquilateralTriangle fromArea(double area) throws IllegalShapeException {
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
        return Utils.mergeProperties(
                Map.of(
                        "Side", getSide(),
                        "Height", getHeight()
                ),
                super.getShapeProperties()
        );
    }
}
