package dev.justpizza.shape;

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

    public static Circle fromCircuit(double circuit) {
        return Circle.fromRadius(circuit / (Math.PI * 2));
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getCircuit() {
        return 2 * Math.PI * radius;
    }

    @Override
    protected Map<String, Object> getProperties() {
        return Map.of("Radius", getRadius(),
                "Area", getArea(),
                "Circuit", getCircuit());
    }

    @Override
    protected String getShapeName() {
        return "Circle";
    }

    @Override
    public double calcArea() {
        return getArea();
    }

    @Override
    public Circle createCircumcircle() {
        return Circle.fromRadius(getRadius());
    }
}
