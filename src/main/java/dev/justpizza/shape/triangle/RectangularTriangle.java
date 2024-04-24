package dev.justpizza.shape.triangle;

import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.utils.Utils;

import java.util.Map;

public class RectangularTriangle extends Triangle{

    public RectangularTriangle(double sideA, double sideB, double sideC) throws IllegalShapeException {
        super(sideA, sideB, sideC);
        if((sideA * sideA) + (sideB * sideB) != (sideC * sideC)){
            throw new IllegalShapeException(paramError("Triangle"));
        }
    }

    public double getArea() {
        return sideA * sideB * 0.5;
    }

    @Override
    public double getPerimeter() {
        return getSideA() + getSideB() + getSideC();
    }

    @Override
    protected Map<String, Object> getProperties() {
        return super.getProperties();
    }

    @Override
    public Shape doubleArea() throws IllegalShapeException {
        double sideA = getSideA() * Math.sqrt(2);
        double sideB = getSideB() * Math.sqrt(2);
        double sideC = Math.sqrt((sideA * sideA) + (sideB * sideB));
        return new RectangularTriangle(sideA, sideB, sideC);
    }
}

