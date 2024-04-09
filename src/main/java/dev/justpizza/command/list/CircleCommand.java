package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.shape.Circle;
import dev.justpizza.shape.Shape;

import java.util.List;

public class CircleCommand extends CreateShapeCommand {
    private static final String name = "circle";
    private static final String description = "calculates circle characteristics";

    public CircleCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.possibleArgs.add(List.of("radius", "area", "circuit"));
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        var radius = argParser.argValues.get("radius");
        var area = argParser.argValues.get("area");
        var circuit = argParser.argValues.get("circuit");

        if (radius != null) {
            return Circle.fromRadius(radius);
        } else if (area != null) {
            return Circle.fromArea(area);
        } else if (circuit != null) {
            return Circle.fromCircuit(circuit);
        } else {
            System.out.println("Not enough characteristics");
            return null;
        }
    }
}
