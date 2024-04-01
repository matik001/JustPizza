package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.shape.EquilateralTriangle;
import dev.justpizza.shape.Shape;

import java.util.List;

public class EquilateralTriangleCommand extends CreateShapeCommand {
    public static final String name = "equtriangle";
public static final String description = "calculates equilateral triangle characteristics";
    public EquilateralTriangleCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.possibleArgs.add(List.of("side", "height", "area"));
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        EquilateralTriangle triangle;
        String argName = argParser.argValues.keySet().iterator().next();
        Double value = argParser.argValues.get(argName);
        switch (argName) {
            case "side" -> triangle = EquilateralTriangle.fromSide(value);
            case "height" -> triangle = EquilateralTriangle.fromHeight(value);
            case "area" -> triangle = EquilateralTriangle.fromArea(value);
            default -> {
                assert false;
                return null;
            }
        }
        return triangle;
    }


}
