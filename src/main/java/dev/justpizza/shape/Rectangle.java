package dev.justpizza.shape;

public class Rectangle {
    private double sideA;
    private double sideB;
    private double diagonal;
    private double area;

    private Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.diagonal = Math.sqrt(sideA * sideA + sideB * sideB);
        this.area = sideA * sideB;
    }

    public static Rectangle fromSides(double sideA, double sideB) {
        return new Rectangle(sideA, sideB);
    }

    public static Rectangle fromDiagonalAndSide(double diagonal, double side) {
        double otherSide = Math.sqrt(diagonal * diagonal - side * side);
        return new Rectangle(side, otherSide);
    }

    public static Rectangle fromAreaAndSide(double area, double side) {
        double otherSide = area / side;
        return new Rectangle(side, otherSide);
    }

    public static Rectangle fromDiagonalAndArea(double diagonal, double area) {
        // TODO - trzeba jakiś układ równań rozwiązać
        assert false;
        return null;
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public double getArea() {
        return area;
    }

    public boolean isSquare() {
        return sideA == sideB;
    }

    public void printCharacteristic() {
        System.out.println("Rectangle characteristics:");
        System.out.println("\tSide A: " + sideA);
        System.out.println("\tSide B: " + sideB);
        System.out.println("\tDiagonal: " + diagonal);
        System.out.println("\tArea: " + area);
    }
}