package dev.justpizza.shape;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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

    synchronized public void addShape(Shape shape) {
        shapesList.add(shape);
    }

    synchronized public void jsonShapesToStream(OutputStream stream) throws IOException {
        var ptv = BasicPolymorphicTypeValidator.builder()
                                               .allowIfSubType("com.baeldung.jackson.inheritance")
                                               .allowIfSubType("java.util.ArrayList")
                                               .build();

        var objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);

        objectMapper.writeValue(stream, shapesList);
    }

    synchronized public void printShapesToStream(OutputStream stream) throws IOException {
        var sw = new OutputStreamWriter(stream);
        for (int i = 0; i < shapesList.size(); i++) {
            sw.append(STR."\{i + 1}. ");
            sw.append(shapesList.get(i).getCharacteristic());
            sw.append('\n');
            sw.flush();
        }
    }

    public void printShapes() {
        try {
            printShapesToStream(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized public void removeShape(int id) {
        id--;
        if (id >= shapesList.size() || id < 0) {
            out.println("Shape with given id doesn't exists");
            return;
        }
        var shape = shapesList.remove(id);
        out.println("Removed:");
        out.println(shape.getCharacteristic());
    }

    synchronized public void sortShapes(String field, boolean increasing) {
        if (increasing) {
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
