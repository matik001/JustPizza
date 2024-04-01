package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.Command;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.shape.*;

import java.util.ArrayList;
import java.util.List;

public class RectangleCommand extends CreateShapeCommand {
    public static final String name = "rectangle";
    public static final String description = "calculates rectangle characteristics";

    public RectangleCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.possibleArgs.add(List.of("sidea", "sideb", "diagonal", "area"));
        argParser.possibleArgs.add(List.of("sidea", "sideb", "diagonal", "area"));
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
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
                return null;
            }
        } else {
            System.out.println("Not enough characteristics");
            return null;
        }
        return shape;
    }

}