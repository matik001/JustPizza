package dev.justpizza.shape.circle;

import dev.justpizza.shape.Shape;
import dev.justpizza.utils.Utils;

import java.util.Map;

public class Circle extends Shape {
    private final double radius;

    private Circle(double radius) {
        this.radius = radius;
    }

    public static Circle fromRadius(double radius) {
        return new Circle(radius);
    }

    public static Circle fromArea(double area) {
        return Circle.fromRadius(Math.sqrt(area / Math.PI));
    }

    public static Circle fromPerimeter(double perimeter) {
        return Circle.fromRadius(perimeter / (Math.PI * 2));
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    protected Map<String, Object> getProperties() {
        return Utils.mergeProperties(
                Map.of(
                        "Radius", getRadius()
                ),
                super.getProperties()
        );
    }

    @Override
    public Circle createCircumcircle() {
        return Circle.fromRadius(getRadius());
    }

    @Override
    public Circle doubleArea() {
        return Circle.fromArea(getArea() * 2);
    }
}
