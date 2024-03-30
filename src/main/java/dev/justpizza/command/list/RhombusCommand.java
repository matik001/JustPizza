package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.Command;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Rhombus;
import dev.justpizza.shape.Shape;
import org.w3c.dom.UserDataHandler;

import java.util.ArrayList;
import java.util.List;

public class RhombusCommand extends CreateShapeCommand {
    private static final String name = "rhombus";
    private static final String description = "calculates rhombus characteristics";

    public RhombusCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.possibleArgs.add(List.of("side", "diagonala", "diagonalb", "area"));
        argParser.possibleArgs.add(List.of("side", "diagonala", "diagonalb", "area"));
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
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
                return null;
            }
        } else if (side != null && diagonal != null) {
            shape = Rhombus.fromSideAndDiagonal(side, diagonal);
        } else if (area != null && diagonal != null) {
            shape = Rhombus.fromDiagonalAndArea(diagonal, area);
        } else {
            System.out.println("Not enough characteristics");
            return null;
        }
        return shape;
    }
}
