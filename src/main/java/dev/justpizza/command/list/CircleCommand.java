package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
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
        argParser.paramsSchemaList.add(List.of(
                new ParamSchema("radius"), new ParamSchema("area"),
                new ParamSchema("circuit")));
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        var radius = argParser.argValues.get("radius");
        var area = argParser.argValues.get("area");
        var circuit = argParser.argValues.get("circuit");

        if (radius != null) {
            return Circle.fromRadius(radius.getDouble());
        } else if (area != null) {
            return Circle.fromArea(area.getDouble());
        } else if (circuit != null) {
            return Circle.fromCircuit(circuit.getDouble());
        } else {
            System.out.println("Not enough characteristics");
            return null;
        }
    }
}
