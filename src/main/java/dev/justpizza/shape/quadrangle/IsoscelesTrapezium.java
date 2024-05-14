package dev.justpizza.shape.quadrangle;

import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.ellipse.Circle;
import dev.justpizza.utils.Utils;

import java.util.Map;

public class IsoscelesTrapezium extends Shape {
    private final double baseA;
    private final double baseB;
    private final double height;
    private final double leg;

    private IsoscelesTrapezium(double baseA, double baseB, double height, double leg) {
        if (baseA <= baseB) {
            this.baseA = baseA;
            this.baseB = baseB;
        } else {
            this.baseA = baseB;
            this.baseB = baseA;
        }
        this.height = height;
        this.leg = leg;
    }

    public static Shape fromBasesAndHeightAndLeg(double baseA, double baseB, double height, double leg) {
        if (height == leg) return Rectangle.fromSides(baseA, height);
        return new IsoscelesTrapezium(baseA, baseB, height, leg);
    }

    public static Shape fromBasesAndHeight(double baseA, double baseB, double height) {
        double x = (baseA - baseB) / 2.0;
        double leg = Math.sqrt(x * x + height * height);
        return IsoscelesTrapezium.fromBasesAndHeightAndLeg(baseA, baseB, height, leg);
    }

    public static Shape fromBasesAndLeg(double baseA, double baseB, double leg) throws IllegalShapeException {
        double basesDifference = baseA - baseB;
        double x = 4 * leg * leg - basesDifference * basesDifference;
        if (x <= 0) {
            throw new IllegalShapeException(paramError(IsoscelesTrapezium.class.getSimpleName()));
        }
        double height = Math.sqrt(x) / 2.0;
        return IsoscelesTrapezium.fromBasesAndHeightAndLeg(baseA, baseB, height, leg);
    }

    public static Shape fromBasesAndArea(double baseA, double baseB, double area) {
        double height = (2.0 * area) / (baseA + baseB);
        return IsoscelesTrapezium.fromBasesAndHeight(baseA, baseB, height);
    }


    public static Shape fromBaseBAndHeightAndLeg(double baseB, double height, double leg) throws IllegalShapeException {
        if (leg < height) {
            throw new IllegalShapeException(paramError(IsoscelesTrapezium.class.getSimpleName()));
        }
        double baseA = baseB - 2 * Math.sqrt(leg * leg - height * height);
        return IsoscelesTrapezium.fromBasesAndHeightAndLeg(baseA, baseB, height, leg);
    }

    public static Shape fromBaseAAndHeightAndLeg(double baseA, double height, double leg) throws IllegalShapeException {
        if (leg < height) {
            throw new IllegalShapeException(paramError(IsoscelesTrapezium.class.getSimpleName()));
        }
        double baseB = baseA + 2 * Math.sqrt(leg * leg - height * height);
        return IsoscelesTrapezium.fromBasesAndHeightAndLeg(baseA, baseB, height, leg);
    }

    public static Shape fromBaseAndHeightAndArea(double baseA, double height, double area) throws IllegalShapeException {
        double baseB = (2 * area) / height - baseA;
        if (baseB <= 0) {
            throw new IllegalShapeException(paramError(IsoscelesTrapezium.class.getSimpleName()));
        }
        return IsoscelesTrapezium.fromBasesAndHeight(baseA, baseB, height);
    }

    public static Shape fromBaseAndLegAndArea(double baseA, double leg, double area) throws IllegalShapeException {
        throw new IllegalShapeException(ambiguousError(IsoscelesTrapezium.class.getSimpleName()));
    }

    public static Shape fromHeightAndLegAndArea(double height, double leg, double area) throws IllegalShapeException {
        if (leg < height) {
            throw new IllegalShapeException(paramError(IsoscelesTrapezium.class.getSimpleName()));
        }
        double p = Math.sqrt(leg * leg - height * height);
        double basesSum = (2.0 * area) / height;
        double baseA = basesSum / 2.0 - p;
        double baseB = basesSum - baseA;
        if (baseA <= 0) {
            throw new IllegalShapeException(paramError(IsoscelesTrapezium.class.getSimpleName()));
        }
        return IsoscelesTrapezium.fromBasesAndHeightAndLeg(baseA, baseB, height, leg);
    }

    public double getBaseA() {
        return baseA;
    }

    public double getBaseB() {
        return baseB;
    }

    public double getHeight() {
        return height;
    }

    public double getLeg() {
        return leg;
    }

    @Override
    public double getArea() {
        return (baseA + baseB) * height / 2.0;
    }

    @Override
    public double getPerimeter() {
        return baseA + baseB + leg * 2;
    }

    @Override
    protected Map<String, Object> getProperties() {
        return Utils.mergeProperties(
                Map.of(
                        "Base A", getBaseA(),
                        "Base B", getBaseB(),
                        "Height", getHeight(),
                        "Leg", getLeg(),
                        "Area", getArea()
                ),
                super.getProperties()
        );
    }

    @Override
    public Circle createCircumcircle() {
        double basesDifference = baseA - baseB;
        double numerator = baseA * baseB + leg * leg;
        double denominator = 4 * leg * leg - basesDifference * basesDifference;
        double radius = leg * Math.sqrt(numerator / denominator);
        return Circle.fromRadius(radius);
    }

    @Override
    public Shape doubleArea() throws IllegalShapeException {
        double sqrt2 = Math.sqrt(2);
        return new IsoscelesTrapezium(baseA * sqrt2, baseB * sqrt2, height * sqrt2, leg * sqrt2);
    }
}
