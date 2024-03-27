package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.Command;
import dev.justpizza.shape.*;

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

        Shape shape;
        var sideA = argParser.argValues.get("sidea");
        var sideB = argParser.argValues.get("sideb");
        var side = sideA != null ? sideA : sideB;
        var diagonal = argParser.argValues.get("diagonal");
        var area = argParser.argValues.get("area");

        if (sideA != null && sideB != null) {
            shape = Rectangle.fromSides(sideA, sideB);
        } else if (side != null && diagonal != null) {
            shape = Rectangle.fromDiagonalAndSide(diagonal, side);
        } else if (side != null && area != null) {
            shape = Rectangle.fromAreaAndSide(area, side);
        } else if (diagonal != null && area != null) {
            try {
                shape = Rectangle.fromDiagonalAndArea(diagonal, area);
            } catch (IllegalShapeException e) {
                System.out.println(e.getMessage());
                return;
            }
        } else {
            System.out.println("Not enough characteristics");
            return;
        }

        shape.printCharacteristic();
    }
}