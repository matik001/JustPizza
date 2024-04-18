package dev.justpizza.command.abstractList;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.Command;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.ShapesManager;

public abstract class CreateShapeCommand extends Command {
    protected CreateShapeCommand(String name, String description) {
        super(name, description);
    }

    protected abstract Shape createShape(ArgParser argParser);

    @Override
    protected boolean handleExecute(ArgParser argParser) {
        var shape = createShape(argParser);
        if (shape == null) return true;
        out.println(shape.getCharacteristic());
        ShapesManager.instance.addShape(shape);
        return true;
    }
}
