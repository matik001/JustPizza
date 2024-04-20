package dev.justpizza.command.abstractList;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.Command;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.ShapesManager;

public abstract class CreateShapeCommand extends Command {
    protected CreateShapeCommand(String name, String description) {
        super(name, description);
    }

    protected abstract Shape createShape(ShapesManager shapesManager, ArgParser argParser);

    @Override
    protected boolean handleExecute(ShapesManager shapesManager, ArgParser argParser) {
        var shape = createShape(shapesManager, argParser);
        if (shape == null) return true;
        out.println(shape.getCharacteristic());
        shapesManager.addShape(shape);
        return true;
    }
}
