package dev.justpizza.shape.triangle;

import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.circle.Circle;
import dev.justpizza.translations.TranslationKey;
import dev.justpizza.utils.Utils;


import java.util.Arrays;
import java.util.Map;

public class Triangle extends Shape {
    private final double sideA, sideB, sideC;

    public Triangle(double sideA, double sideB, double sideC) throws IllegalShapeException {
        double[] array = {sideA, sideB, sideC};
        Arrays.sort(array);
        this.sideA = array[0];
        this.sideB = array[1];
        this.sideC = array[2];

        if (sideA + sideB <= sideC) {
            throw new IllegalShapeException(paramError("Triangle"));
        }
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getSideC() {
        return sideC;
    }

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

    @Override
    public Shape doubleArea() throws IllegalShapeException {
        return new Triangle(
                getSideA() * Math.sqrt(2),
                getSideB() * Math.sqrt(2),
                getSideC() * Math.sqrt(2));
    }
}
