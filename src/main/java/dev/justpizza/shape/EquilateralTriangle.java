package dev.justpizza.shape;

public class EquilateralTriangle extends Shape {
    private final double side;

    public EquilateralTriangle(double side) {
        this.side = side;
    }

    public static EquilateralTriangle fromSide(double side) {
        return new EquilateralTriangle(side);
    }

    public static EquilateralTriangle fromHeight(double height) {
        return new EquilateralTriangle(height * 2 / Math.sqrt(3.0));
    }

    public static EquilateralTriangle fromArea(double area) {
        return new EquilateralTriangle(Math.sqrt(4 * area / Math.sqrt(3)));
    }

    public double getSide() {
        return side;
    }

    public double getHeight() {
        return side * Math.sqrt(3) / 2;
    }

    public double getArea() {
        return side * side * Math.sqrt(3) / 4;
    }

    @Override
    public void printCharacteristic() {
        System.out.println(STR. "Triangle characteristics:\n\tside: \{ getSide() }\n\theight: \{ getHeight() }\n\tarea: \{ getArea() }\n" );
    }
}
