package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.Command;
import dev.justpizza.shape.Square;

import java.util.ArrayList;
import java.util.List;

public class SquareCommand extends Command {

    public static final String name = "square";

    public SquareCommand() {
        super(name, "calculates square characteristics");
    }

    @Override
    public void execute(String[] params) {
        ArgParser argParser = new ArgParser();
        List<List<String>> possibleParams = new ArrayList();
        possibleParams.add(List.of("side", "diagonal", "area"));

        try {
            argParser.parseParams(possibleParams, params, name);
        } catch (IllegalArgumentException exc) {
            System.out.println(exc.getMessage());
            return;
        }

        Square square;
        String argName = argParser.argValues.keySet().iterator().next();
        Double value = argParser.argValues.get(argName);
        switch (argName) {
            case "side" -> square = Square.fromSide(value);
            case "diagonal" -> square = Square.fromDiagonal(value);
            case "area" -> square = Square.fromArea(value);
            default -> {
                assert false;
                return;
            }
        }
        square.printCharacteristic();
    }
}
