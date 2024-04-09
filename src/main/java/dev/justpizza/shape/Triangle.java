package dev.justpizza.shape;

public abstract class Triangle extends Shape {
    public abstract double getSideA();

    public abstract double getSideB();

    public abstract double getSideC();

    @Override
    public Circle createCircumcircle() {
        double p = getSideA() * getSideB() * getSideC();
        return Circle.fromRadius(p / (4 * calcArea()));
    }
}
