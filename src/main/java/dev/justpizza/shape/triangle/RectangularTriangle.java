package dev.justpizza.shape.triangle;

import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.utils.Utils;

import java.util.Map;

public class RectangularTriangle extends Triangle {

    private RectangularTriangle(double base, double altitude, double hypotenuse) throws IllegalShapeException {
        super(base, altitude, hypotenuse);
    }

    private static Triangle fromBaseAltitudeHypotenuse(double base, double altitude, double hypotenuse) throws IllegalShapeException {
        if (Utils.areClose(base, altitude)) return IsoscelesTriangle.fromBaseSide(hypotenuse, (base + altitude) / 2);
        return new dev.justpizza.shape.triangle.RectangularTriangle(base, altitude, hypotenuse);
    }

    public static Triangle fromBaseAltitude(double base, double altitude) throws IllegalShapeException {
        double hypotenuse = Math.sqrt((base * base) + (altitude * altitude));
        return dev.justpizza.shape.triangle.RectangularTriangle.fromBaseAltitudeHypotenuse(base, altitude, hypotenuse);
    }

    public static Triangle fromBaseHypotenuse(double base, double hypotenuse) throws IllegalShapeException {
        if (base >= hypotenuse) {
            throw new IllegalShapeException(paramError(dev.justpizza.shape.triangle.RectangularTriangle.class.getSimpleName()));
        }
        double altitude = Math.sqrt((hypotenuse * hypotenuse) - (base * base));
        return dev.justpizza.shape.triangle.RectangularTriangle.fromBaseAltitudeHypotenuse(base, altitude, hypotenuse);
    }

    public static Triangle fromBaseArea(double base, double area) throws IllegalShapeException {
        double altitude = 2 * area / base;
        return fromBaseAltitude(base, altitude);
    }

    public static Triangle fromAltitudeHypotenuse(double altitude, double hypotenuse) throws IllegalShapeException {
        return fromBaseHypotenuse(altitude, hypotenuse);
    }

    public static Triangle fromAltitudeArea(double altitude, double area) throws IllegalShapeException {
        return fromBaseArea(altitude, area);
    }

    public static Triangle fromHypotenuseArea(double hypotenuse, double area) throws IllegalShapeException {
        double delta = Math.pow(hypotenuse, 4) - (16 * Math.pow(area, 2));
        if (delta < 0) {
            throw new IllegalShapeException(paramError(dev.justpizza.shape.triangle.RectangularTriangle.class.getSimpleName()));
        }
        double base = Math.sqrt((Math.pow(hypotenuse, 2) + Math.sqrt(delta)) / 2);
        double altitude = Math.sqrt(Math.pow(hypotenuse, 2) - Math.pow(base, 2));
        return dev.justpizza.shape.triangle.RectangularTriangle.fromBaseAltitudeHypotenuse(base, altitude, hypotenuse);
    }

    public double getBase() {
        return getSideA();
    }

    public double getAltitude() {
        return getSideB();
    }

    public double getHypotenuse() {
        return getSideC();
    }

    public double getArea() {
        return getBase() * getAltitude() * 0.5;
    }

    @Override
    protected Map<String, Object> getProperties() {
        return Utils.mergeProperties(
                Map.of(
                        "Base", getBase(),
                        "Altitude", getAltitude(),
                        "Hypotenuse", getHypotenuse()
                ),
                super.getShapeProperties()
        );
    }

    @Override
    public Shape doubleArea() throws IllegalShapeException {
        double sideA = getSideA() * Math.sqrt(2);
        double sideB = getSideB() * Math.sqrt(2);
        double sideC = Math.sqrt((sideA * sideA) + (sideB * sideB));
        return new dev.justpizza.shape.triangle.RectangularTriangle(sideA, sideB, sideC);
    }
}

