package dev.justpizza.shape;

import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
            shapesList.get(i).printCharacteristic();
        }
    }

    public void sortShapes(String field, boolean increasing) {
        if(increasing) {
            switch (field) {
                case "area":
                    shapesList.sort(Comparator.comparingDouble(Shape::getArea));
                    break;
                case "perimeter":
                    shapesList.sort(Comparator.comparingDouble(Shape::getPerimeter));
                    break;
                default:
                    shapesList.sort(Comparator.comparing(Shape::getDate));
            }
        } else {
            switch (field) {
                case "area":
                    shapesList.sort(Comparator.comparingDouble(Shape::getArea).reversed());
                    break;
                case "perimeter":
                    shapesList.sort(Comparator.comparingDouble(Shape::getPerimeter).reversed());
                    break;
                default:
                    shapesList.sort(Comparator.comparing(Shape::getDate).reversed());
            }
        }
    }

    public Shape get(int i) {
        return shapesList.get(i);
    }

    public int size() {
        return shapesList.size();
    }
}
