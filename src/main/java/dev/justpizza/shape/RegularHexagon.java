package dev.justpizza.shape;

import dev.justpizza.shape.ellipse.Circle;
import dev.justpizza.utils.Utils;

import java.util.Map;

public class RegularHexagon extends Shape {
    private final double side;

    private RegularHexagon(double side) throws IllegalShapeException {
        if (Utils.areClose(0, side))
        {
            throw new IllegalShapeException("Side cannot be 0");
        }
        this.side = side;
    }

    public static RegularHexagon fromSide(double side) throws IllegalShapeException {
        return new RegularHexagon(side);
    }

    public static RegularHexagon fromPerimeter(double perimeter) throws IllegalShapeException {
        return new RegularHexagon(perimeter / 6);
    }

    public static RegularHexagon fromArea(double area) throws IllegalShapeException {
        double triangle_area = area / 6;
        return new RegularHexagon(Math.sqrt(4 * triangle_area / Math.sqrt(3)));
    }

    public double getSide() {
        return side;
    }

    @Override
    protected Map<String, Object> getProperties() {
        return Utils.mergeProperties(
                Map.of(
                        "Side", getSide()
                ),
                super.getProperties()
        );
    }

    @Override
    public double getArea() {
        return 3 * Math.sqrt(3) * Math.pow(side, 2) / 2;
    }

    @Override
    public double getPerimeter() {
        return 6 * side;
    }

    @Override
    public Circle createCircumcircle() throws IllegalShapeException {
        return Circle.fromRadius(side);
    }

    public Shape doubleArea() throws IllegalShapeException {
        return RegularHexagon.fromArea(getArea() * 2);
    }
}
