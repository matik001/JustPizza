package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.Command;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Rhombus;
import dev.justpizza.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class RhombusCommand extends Command {
    private static final String name = "rhombus";
    private static final String description = "rhombus";

    public RhombusCommand() {
        super(name, description);
    }

    @Override
    public void execute(String[] params) {
        ArgParser argParser = new ArgParser();
        List<List<String>> possibleParams = new ArrayList();
        possibleParams.add(List.of("side", "diagonala", "diagonalb", "area"));
        possibleParams.add(List.of("side", "diagonala", "diagonalb", "area"));

        try {
            argParser.parseParams(possibleParams, params, name);
        } catch (IllegalArgumentException exc) {
            System.out.println(exc.getMessage());
            return;
        }

        Shape shape;
        var side = argParser.argValues.get("side");
        var diagonala = argParser.argValues.get("diagonala");
        var diagonalb = argParser.argValues.get("diagonalb");
        var diagonal = diagonala != null ? diagonala : diagonalb;
        var area = argParser.argValues.get("area");



        if (diagonala != null && diagonalb != null) {
            shape = Rhombus.fromDiagonals(diagonala, diagonalb);
        } else if (side != null && area != null) {
            try {
                shape = Rhombus.fromSideAndArea(side, area);
            } catch (IllegalShapeException e) {
                System.out.println(e.getMessage());
                return;
            }
        } else if (side != null && diagonal != null) {
            shape = Rhombus.fromSideAndDiagonal(side, diagonal);
        } else if (area != null && diagonal != null) {
            shape = Rhombus.fromDiagonalAndArea(diagonal, area);
        } else {
            System.out.println("Not enough characteristics");
            return;
        }

        shape.printCharacteristic();
    }
}
