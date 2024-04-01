package dev.justpizza.shape;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShapesManager {
    List<Shape> shapesList = new ArrayList<>();
    public static ShapesManager instance = new ShapesManager(); /// mozna dodac do commandManager zamiast robic statyczne

    public void addShape(Shape shape) {
        shapesList.add(shape);
    }

    public void printShapes() {
        for (int i = 0; i < shapesList.size(); i++) {
            System.out.print(STR."\{i + 1}e. ");
            shapesList.get(i).printCharacteristic();
        }
    }

    public void sortShapes() {
        shapesList.sort(Comparator.comparingDouble(Shape::getArea));
    }

    public Shape get(int i) {
        return shapesList.get(i);
    }

    public int size() {
        return shapesList.size();
    }
}
