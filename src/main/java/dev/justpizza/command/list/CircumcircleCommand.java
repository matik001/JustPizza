package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.argparser.ParamType;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.ShapesManager;

import java.util.List;

public class CircumcircleCommand extends CreateShapeCommand {
    public static final String name = "circumcircle";
    public static final String description = "creates circumcircle";

    public CircumcircleCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.paramsSchemaList.add(List.of(new ParamSchema("shapenumber", ParamType.INT)));
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        var shapeNumber = argParser.argValues.get("shapenumber").getInt();
        var shapesManagerSize = ShapesManager.instance.size();
        if (shapesManagerSize == 0) {
            System.out.println(STR."There are 0 shapes stored. Can't create circumcircle\n");
            return null;
        }

        if (shapeNumber < 1 || shapesManagerSize < shapeNumber) {
            System.out.println(STR."There are \{shapesManagerSize} shapes stored\n expected number in range [1, \{shapesManagerSize}]\n");
            return null;
        }

        var shape = ShapesManager.instance.get(shapeNumber - 1);
        return shape.createCircumcircle();

    }

}
