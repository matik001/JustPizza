package dev.justpizza.shape;

public class Circle extends Shape {
    private final double radius;

    private Circle(double radius) {
        this.radius = radius;
    }

    public static Shape fromRadius(double radius) {
        return new Circle(radius);
    }

    public static Shape fromArea(double area) {
        return Circle.fromRadius(Math.sqrt(area / Math.PI));
    }

    public static Shape fromCircuit(double circuit) {
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
    public void printCharacteristic() {
        System.out.println("Rectangle characteristics:");
        System.out.println("\tRadius: " + getRadius());
        System.out.println("\tArea: " + getArea());
        System.out.println("\tCircuit: " + getCircuit());
    }

    @Override
    public double calcArea() {
        return getArea();
    }
}
