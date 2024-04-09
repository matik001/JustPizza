package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.Command;
import dev.justpizza.shape.ShapesManager;

import java.util.ArrayList;
import java.util.List;

public class ShapesCommand extends Command {
    public static final String name = "shapes";
    public static final String description = "prints list of shapes";

    public ShapesCommand() {
        super(name, description);
    }

    @Override
    public void execute(String[] params) {
        ShapesManager.instance.printShapes();
    }
}
