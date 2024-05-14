package dev.justpizza.shape.ellipse;

import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.utils.Utils;

import java.util.Map;

public class Ellipse extends Shape {
    private final double semiMajorAxis;
    private final double semiMinorAxis;
    private Double perimeter = null;

    private Ellipse(double semiMajorAxis, double semiMinorAxis) {
        if (semiMajorAxis < semiMinorAxis) {
            this.semiMajorAxis = semiMinorAxis;
            this.semiMinorAxis = semiMajorAxis;
        } else {
            this.semiMajorAxis = semiMajorAxis;
            this.semiMinorAxis = semiMinorAxis;
        }
    }

    public static Shape fromSemiMajorMinor(double semiMajorAxis, double semiMinorAxis) throws IllegalShapeException {
        if (Math.min(semiMajorAxis, semiMinorAxis) < AppSettings.global.getDoubleTolerance()) {
            throw new IllegalShapeException(paramError("Ellipse"));
        }
        if (Utils.areClose(semiMajorAxis, semiMinorAxis)) {
            return Circle.fromRadius(semiMajorAxis);
        }
        return new Ellipse(semiMajorAxis, semiMinorAxis);
    }

    public static Shape fromSemiAxisAndArea(double semiAxis, double area) throws IllegalShapeException {
        return fromSemiMajorMinor(semiAxis, area / Math.PI / semiAxis);
    }

    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public double getSemiMinorAxis() {
        return semiMinorAxis;
    }

    @Override
    public double getArea() {
        return Math.PI * semiMajorAxis * semiMinorAxis;
    }

    @Override
    public double getPerimeter() {
        if (perimeter == null) {
            perimeter = 0.0;
            int steps = 100000;
            double e_squared = Math.pow(getEccentricity(), 2);
            double step_width = 1.0 / steps;
            for (int i = 0; i < steps; i++) {
                double t_squared = Math.pow(step_width * (2 * i + 1) / 2, 2);
                perimeter += Math.sqrt(1 - e_squared * t_squared) / Math.sqrt(1 - t_squared);
            }
            perimeter /= steps;
            perimeter *= 4 * semiMajorAxis;
        }
        return perimeter;
    }

    @Override
    protected Map<String, Object> getProperties() {
        return Utils.mergeProperties(
                Map.of(
                        "semiMajorAxis", getSemiMajorAxis(),
                        "semiMinorAxis", getSemiMinorAxis()
                ),
                super.getProperties()
        );
    }

    @Override
    public Circle createCircumcircle() {
        return Circle.fromRadius(getSemiMajorAxis());
    }

    @Override
    public Shape doubleArea() {
        double sqrt2 = Math.sqrt(2);
        return new Ellipse(
                getSemiMajorAxis() * sqrt2,
                getSemiMinorAxis() * sqrt2
        );
    }

    public double getEccentricity() {
        return Math.sqrt(1 - Math.pow(semiMinorAxis / semiMajorAxis, 2));
    }
}
