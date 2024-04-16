package dev.justpizza.command.abstractList;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.Command;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.ShapesManager;

public abstract class CreateShapeCommand extends Command {
    protected CreateShapeCommand(String name, String description) {
        super(name, description);
    }
    protected abstract void initArgParser(ArgParser argParser);
    protected abstract Shape createShape(ArgParser argParser);
    @Override
    public void execute(String[] params) {
        var argParser = new ArgParser();
        initArgParser(argParser);

        try {
            argParser.parseParams(params, name);
        } catch (IllegalArgumentException exc) {
            System.out.println(exc.getMessage());
            return;
        }


        var shape = createShape(argParser);
        if(shape == null)
            return;
        shape.printCharacteristic();
        ShapesManager.instance.addShape(shape);
    }
}
