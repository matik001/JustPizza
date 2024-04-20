package dev.justpizza.shape;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShapesManager {
    private final PrintStream out;
    List<Shape> shapesList = new ArrayList<>();

    public ShapesManager(PrintStream out) {
        this.out = out;
    }
//    public static ShapesManager instance = new ShapesManager();

    public void addShape(Shape shape) {
        shapesList.add(shape);
    }

    public void printShapes() {
        for (int i = 0; i < shapesList.size(); i++) {
            out.print(STR."\{i + 1}. ");
            out.println(shapesList.get(i).getCharacteristic());
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
