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
            System.out.print(STR."\{i + 1}. ");
            System.out.println(shapesList.get(i).getCharacteristic());

        }
    }

    public void sortShapes(boolean by_area, boolean increasing) {
        if (increasing) {
            shapesList.sort(Comparator.comparingDouble(by_area ? Shape::getArea : Shape::getPerimeter));
        } else {
            shapesList.sort(Comparator.comparingDouble(by_area ? Shape::getArea : Shape::getPerimeter).reversed());
        }
    }

    public Shape get(int i) {
        return shapesList.get(i);
    }

    public int size() {
        return shapesList.size();
    }
}
