package dev.justpizza.shape;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class ShapesManager {
    List<Shape> shapesList = new ArrayList<>();
    public static ShapesManager instance = new ShapesManager(); /// mozna dodac do commandManager zamiast robic statyczne

    synchronized public void addShape(Shape shape) {
        shapesList.add(shape);
    }

    synchronized public void printShapesToStream(OutputStream stream) throws IOException {
        var sw = new OutputStreamWriter(stream);
        for (int i = 0; i < shapesList.size(); i++) {
            sw.append(STR."\{i + 1}. ");
            sw.flush();
            shapesList.get(i).printCharacteristicToStream(stream);
        }
    }
    public void printShapes() {
        try {
            printShapesToStream(System.out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized public void sortShapes(String field, boolean increasing) {
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

    synchronized public Shape get(int i) {
        return shapesList.get(i);
    }

    synchronized public int size() {
        return shapesList.size();
    }
}
