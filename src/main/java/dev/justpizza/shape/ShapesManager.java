package dev.justpizza.shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShapesManager {
    List<Shape> shapesList = new ArrayList<>();
    public static ShapesManager instance = new ShapesManager(); /// mozna dodac do commandManager zamiast robic statyczne
    public void addShape(Shape shape){
        shapesList.add(shape);
        shapesList.sort(Comparator.comparingDouble(Shape::calcArea)); /// mozna zrobic wydajniej
    }

    public void printShapes(){
        for (var shape : shapesList){
            shape.printCharacteristic();
        }
    }

    public Shape get(int i) {
        return shapesList.get(i);
    }
}
