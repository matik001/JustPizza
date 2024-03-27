package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.Command;
import dev.justpizza.shape.EquilateralTriangle;
import dev.justpizza.shape.Rectangle;
import dev.justpizza.shape.Square;

import java.util.ArrayList;
import java.util.List;

public class RectangleCommand extends Command {
    public static final String name = "rectangle";

    public RectangleCommand() {
        super(name, "calculates rectangle characteristics");
    }

    @Override
    public void execute(String[] params) {
        ArgParser argParser = new ArgParser();
        List<List<String>> possibleParams = new ArrayList();
        possibleParams.add(List.of("sidea", "sideb", "diagonal", "area"));
        possibleParams.add(List.of("sidea", "sideb", "diagonal", "area"));

        try {
            argParser.parseParams(possibleParams, params, name);
        } catch (IllegalArgumentException exc) {
            System.out.println(exc.getMessage());
            return;
        }

        Rectangle rectangle;
        var sideA = argParser.argValues.get("sidea");
        var sideB = argParser.argValues.get("sideb");
        var diagonal = argParser.argValues.get("diagonal");
        var area = argParser.argValues.get("area");

        if (sideA != null && sideB != null) {
            rectangle = Rectangle.fromSides(sideA, sideB);
        } else if (sideA != null || sideB != null) {
            Double side = sideA != null ? sideA : sideB;

            if (diagonal != null) {
                rectangle = Rectangle.fromDiagonalAndSide(diagonal, side);
            } else {
                rectangle = Rectangle.fromAreaAndSide(area, side);
            }
        } else {
            assert diagonal != null;
            assert area != null;
            rectangle = Rectangle.fromDiagonalAndArea(diagonal, area);
        }

        if (rectangle.getSideA() == rectangle.getSideB()) {
            Square square = Square.fromSide(rectangle.getSideA());
            square.printCharacteristic();
        } else {
            rectangle.printCharacteristic();
        }
    }
}