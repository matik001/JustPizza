package dev.justpizza.shape.triangle;

import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.utils.Utils;

import java.util.Map;

public class RectangularTriangle extends Triangle {

    public RectangularTriangle(double sideA, double sideB, double sideC) throws IllegalShapeException {
        super(sideA, sideB, sideC);
    }


    public static RectangularTriangle fromBaseAltitude(double base, double altitude) {
        double hypotenuse = Math.sqrt((base * base) + (altitude * altitude));
        try {
            return new RectangularTriangle(base, altitude, hypotenuse);
        } catch (IllegalShapeException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static RectangularTriangle fromBaseHypotenuse(double base, double hypotenuse) throws IllegalShapeException {
        if (base > hypotenuse) {
            throw new IllegalShapeException(paramError("Triangle"));
        }
        double altitude = Math.sqrt((hypotenuse * hypotenuse) - (base * base));
        try {
            return new RectangularTriangle(base, altitude, hypotenuse);
        } catch (IllegalShapeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static RectangularTriangle fromBaseArea(double base, double area) {
        double altitude = 2 * area / base;
        double hypotenuse = Math.sqrt((base * base) + (altitude * altitude));
        try {
            return new RectangularTriangle(base, altitude, hypotenuse);
        } catch (IllegalShapeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static RectangularTriangle fromAltitudeHypotenuse(double altitude, double hypotenuse) throws IllegalShapeException {
        if (altitude > hypotenuse) {
            throw new IllegalShapeException(paramError("Triangle"));
        }
        double base = Math.sqrt((hypotenuse * hypotenuse) - (altitude * altitude));
        try {
            return new RectangularTriangle(base, altitude, hypotenuse);
        } catch (IllegalShapeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static RectangularTriangle fromAltitudeArea(double altitude, double area) {
        double base = 2 * area / altitude;
        double hypotenuse = Math.sqrt((base * base) + (altitude * altitude));
        try {
            return new RectangularTriangle(base, altitude, hypotenuse);
        } catch (IllegalShapeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static RectangularTriangle fromHypotenuseArea(double hypotenuse, double area) throws IllegalShapeException {
        double delta = Math.pow(hypotenuse, 4) - (16 * Math.pow(area, 2));
        double k_1 = (Math.pow(hypotenuse, 2) - Math.sqrt(delta)) / 2;
        double k_2 = (Math.pow(hypotenuse, 2) + Math.sqrt(delta)) / 2;
        if (k_1 > k_2) {
            double base = Math.sqrt(k_1);
            double altitude = Math.sqrt(Math.pow(hypotenuse, 2) - Math.pow(base, 2));
            return new RectangularTriangle(base, altitude, hypotenuse);
        } else {
            double base = Math.sqrt(k_2);
            double altitude = Math.sqrt(Math.pow(hypotenuse, 2) - Math.pow(base, 2));
            return new RectangularTriangle(base, altitude, hypotenuse);
        }
    }

    public double getArea() {
        return sideA * sideB * 0.5;
    }

    @Override
    protected Map<String, Object> getProperties() {
        return Utils.mergeProperties(
                Map.of(
                        "Base", getSideA(),
                        "Altitude", getSideB(),
                        "Hypotenuse", getSideC()
                ),
                super.getShapeProperties()
        );
    }

    @Override
    public Shape doubleArea() throws IllegalShapeException {
        double sideA = getSideA() * Math.sqrt(2);
        double sideB = getSideB() * Math.sqrt(2);
        double sideC = Math.sqrt((sideA * sideA) + (sideB * sideB));
        return new RectangularTriangle(sideA, sideB, sideC);
    }
}

