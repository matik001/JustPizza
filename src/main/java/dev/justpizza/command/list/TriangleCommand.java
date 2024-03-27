package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.Command;
import dev.justpizza.shape.EquilateralTriangle;
import dev.justpizza.shape.Square;

import java.util.ArrayList;
import java.util.List;

public class TriangleCommand extends Command {
    public static final String name = "triangle";

    public TriangleCommand() {
        super(name, "calculates triangle characteristics");
    }

    @Override
    public void execute(String[] params) {
        ArgParser argParser = new ArgParser();
        List<List<String>> possibleParams = new ArrayList();
        possibleParams.add(List.of("side", "height", "area"));

        try {
            argParser.parseParams(possibleParams, params, name);
        } catch (IllegalArgumentException exc) {
            System.out.println(exc.getMessage());
            return;
        }

        EquilateralTriangle triangle;
        String argName = argParser.argValues.keySet().iterator().next();
        Double value = argParser.argValues.get(argName);
        switch (argName) {
            case "side" -> triangle = EquilateralTriangle.fromSide(value);
            case "height" -> triangle = EquilateralTriangle.fromHeight(value);
            case "area" -> triangle = EquilateralTriangle.fromArea(value);
            default -> {
                assert false;
                return;
            }
        }
        triangle.printCharacteristic();
    }
}
