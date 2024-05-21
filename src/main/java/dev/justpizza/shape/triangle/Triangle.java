package dev.justpizza.shape.triangle;

import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.ellipse.Circle;
import dev.justpizza.utils.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Triangle extends Shape {
    protected final double sideA, sideB, sideC;

    public Triangle(double sideA, double sideB, double sideC) throws IllegalShapeException {
        double[] array = {sideA, sideB, sideC};
        Arrays.sort(array);
        this.sideA = array[0];
        this.sideB = array[1];
        this.sideC = array[2];

        if (Utils.areClose(0, this.sideA)) {
            throw new IllegalShapeException("Side cannot be 0");
        }

        if (this.sideA + this.sideB <= this.sideC) {
            throw new IllegalShapeException(paramError("Triangle"));
        }
    }

    public static Triangle fromSides(double sideA, double sideB, double sideC) throws IllegalShapeException {
        double[] sides = {sideA, sideB, sideC};
        Arrays.sort(sides);
        if (Utils.areClose(sides[0], sides[2])) {
            return EquilateralTriangle.fromSide(sides[1]);
        } else if (Utils.areClose(sides[0], sides[1])) {
            return IsoscelesTriangle.fromBaseSide(sides[2], (sides[0] + sides[1]) / 2);
        } else if (Utils.areClose(sides[1], sides[2])) {
            return IsoscelesTriangle.fromBaseSide(sides[0], (sides[1] + sides[2]) / 2);
        } else if (Utils.areClose(Math.sqrt(sides[0] * sides[0] + sides[1] * sides[1]), sides[2])) {
            return RectangularTriangle.fromBaseAltitude(sides[0], sides[1]);
        }
        return new Triangle(sideA, sideB, sideC);
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

    @Override
    public boolean equals(Shape other) {
        if (other instanceof Triangle otherTriangle) {
            List<Double> sides = getSides();
            List<Double> otherSides = otherTriangle.getSides();

            for (int i = 0; i < sides.size(); i++) {
                if (!Utils.areClose(sides.get(i), otherSides.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public List<Double> getSides() {
        List<Double> sides = Arrays.asList(getSideA(), getSideB(), getSideC());
        Collections.sort(sides);
        return sides;
    }
}
