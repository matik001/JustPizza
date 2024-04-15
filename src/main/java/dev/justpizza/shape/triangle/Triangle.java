package dev.justpizza.shape.triangle;

import dev.justpizza.shape.Shape;
import dev.justpizza.shape.circle.Circle;

public abstract class Triangle extends Shape {
    public abstract double getSideA();

    public abstract double getSideB();

    public abstract double getSideC();

    @Override
    public double getArea() {
        double p = (getSideA() + getSideB() + getSideC()) / 2;
        return Math.sqrt(p * (p - getSideA()) * (p - getSideB()) * (p - getSideC()));
    }

    @Override
    public Circle createCircumcircle() {
        double p = getSideA() * getSideB() * getSideC();
        return Circle.fromRadius(p / (4 * getArea()));
    }
}
