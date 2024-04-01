package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.Command;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.Square;

import java.util.ArrayList;
import java.util.List;

public class SquareCommand extends CreateShapeCommand {

    public static final String name = "square";
    public static final String description = "calculates square characteristics";

    public SquareCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.possibleArgs.add(List.of("side", "diagonal", "area"));
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        Square square;
        String argName = argParser.argValues.keySet().iterator().next();
        Double value = argParser.argValues.get(argName);
        switch (argName) {
            case "side" -> square = Square.fromSide(value);
            case "diagonal" -> square = Square.fromDiagonal(value);
            case "area" -> square = Square.fromArea(value);
            default -> {
                assert false;
                return null;
            }
        }
        return square;
    }
}
