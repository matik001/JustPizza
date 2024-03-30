package dev.justpizza.shape;

public class IsoscelesTriangle extends Shape {
    private final double base;
    private final double height;

    public IsoscelesTriangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public static IsoscelesTriangle fromBaseHeight(double base, double height) {
        return new IsoscelesTriangle(base, height);
    }

    public static IsoscelesTriangle fromBaseSide(double base, double side) {
        double height = Math.sqrt(Math.pow(side, 2) - Math.pow(base / 2, 2));
        return new IsoscelesTriangle(base, height);
    }

    public static IsoscelesTriangle fromBaseArea(double base, double area) {
        double height = 2 * area / base;
        return new IsoscelesTriangle(base, height);
    }
    public static IsoscelesTriangle fromHeightArea(double height, double area) {
        double base = 2 * area / height;
        return new IsoscelesTriangle(base, height);
    }
    public static IsoscelesTriangle fromHeightSide(double height, double side) {
        double base = Math.sqrt(Math.pow(side, 2) - Math.pow(height, 2));
        return new IsoscelesTriangle(base, height);
    }

    public static IsoscelesTriangle fromSideArea(double side, double area) {
        double height = 2 * area / side;
        double base = Math.sqrt(4 * Math.pow(height, 2) + Math.pow(side, 2));
        return new IsoscelesTriangle(base, height);
    }

    public double getBase() {
        return base;
    }
    public double getSide() {
        return Math.sqrt(Math.pow(base / 2, 2) + Math.pow(height, 2));
    }

    public double getHeight() {
        return height;
    }

    public double getArea() {
        return base*height/2;
    }

    @Override
    public void printCharacteristic() {
        System.out.println(STR. "Triangle characteristics:\n\tbase: \{ getBase() } \n\tside: \{ getSide() }\n\theight: \{ getHeight() }\n\tarea: \{ getArea() }\n" );
    }

    @Override
    public double calcArea() {
        return getArea();
    }
}
