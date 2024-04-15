package dev.justpizza.shape.triangle;

import dev.justpizza.shape.Shape;
import dev.justpizza.shape.circle.Circle;
import dev.justpizza.utils.Utils;

import java.util.Map;

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
    public double getPerimeter() {
        return getSideA() + getSideB() + getSideC();
    }

    @Override
    protected Map<String, Object> getProperties() {
        return Utils.mergeProperties(
                Map.of(
                        "Side A", getSideA(),
                        "Side B", getSideB(),
                        "Side C", getSideC()
                ),
                super.getProperties()
        );
    }

    protected Map<String, Object> getShapeProperties() {
        return super.getProperties();
    }

    @Override
    public Circle createCircumcircle() {
        double p = getSideA() * getSideB() * getSideC();
        return Circle.fromRadius(p / (4 * getArea()));
    }
}
