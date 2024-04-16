package dev.justpizza.shape.triangle;

import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.translations.TranslationKey;
import dev.justpizza.utils.Utils;

import java.util.Map;

public class IsoscelesTriangle extends Triangle {
    private final double base;
    private final double height;
    private final double side;

    public IsoscelesTriangle(double base, double height) throws IllegalShapeException {
        super(base,
                Math.sqrt(Math.pow(base / 2, 2) + Math.pow(height, 2)),
                Math.sqrt(Math.pow(base / 2, 2) + Math.pow(height, 2))
        );
        this.side = getSideB();
        this.base = base;
        this.height = height;
    }

    public static IsoscelesTriangle fromBaseHeight(double base, double height) throws IllegalShapeException {
        return new IsoscelesTriangle(base, height);
    }

    public static IsoscelesTriangle fromBaseSide(double base, double side) throws IllegalShapeException {
        if (2 * side < base) {
            throw new IllegalShapeException(paramError("IsoscelesTriangle"));
        }

        double height = Math.sqrt(Math.pow(side, 2) - Math.pow(base / 2, 2));
        return new IsoscelesTriangle(base, height);
    }

    public static IsoscelesTriangle fromBaseArea(double base, double area) throws IllegalShapeException {
        double height = 2 * area / base;
        return new IsoscelesTriangle(base, height);
    }

    public static IsoscelesTriangle fromHeightArea(double height, double area) throws IllegalShapeException {
        double base = 2 * area / height;
        return new IsoscelesTriangle(base, height);
    }

    public static IsoscelesTriangle fromHeightSide(double height, double side) throws IllegalShapeException {
        if (side < height) {
            throw new IllegalShapeException(paramError("IsoscelesTriangle"));
        }
        double base = Math.sqrt(Math.pow(side, 2) - Math.pow(height, 2)) * 2;
        return new IsoscelesTriangle(base, height);
    }

    public static IsoscelesTriangle fromSideArea(double side, double area) throws IllegalShapeException {
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
}