package dev.justpizza.shape;

public class Square extends Shape {
    private final double side;

    public Square(double side) {
        this.side = side;
    }

    public static Square fromSide(double side) {
        return new Square(side);
    }

    public static Square fromDiagonal(double side) {
        return new Square(side / Math.sqrt(2));
    }

    public static Square fromArea(double area) {
        return new Square(Math.sqrt(area));
    }

    public double getSide() {
        return side;
    }

    public double getArea() {
        return side * side;
    }

    public double getDiagonal() {
        return side * Math.sqrt(2);
    }

    public void printCharacteristic() {
        System.out.println(
                STR. "Square characteristics:\n\tside: \{ getSide() }\n\tdiagonal: \{ getDiagonal() }\n\tarea: \{ getArea() }\n" );
    }
}
