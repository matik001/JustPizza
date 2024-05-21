package dev.justpizza.command.list.ellipse;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.shape.ellipse.Circle;
import dev.justpizza.shape.Shape;
import dev.justpizza.translations.TranslationKey;

public class CircleCommand extends CreateShapeCommand {
    private static final String name = "circle";
    public CircleCommand() {
        super(name);
    }
    @Override
    public String getDescription() {
        return AppSettings.global.translations.get(TranslationKey.circle_description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("radius", true));
        argParser.addParamSchema(new ParamSchema("area", true));
        argParser.addParamSchema(new ParamSchema("perimeter", true));
        argParser.minNumberOfArgs = 1;
        argParser.maxNumberOfArgs = 1;
    }

    @Override
    protected Shape createShape(ShapesManager shapesManager, ArgParser argParser) {
        var radius = argParser.getValue("radius");
        var area = argParser.getValue("area");
        var perimeter = argParser.getValue("perimeter");

        if (radius != null) {
            return Circle.fromRadius(radius.getDouble());
        } else if (area != null) {
            return Circle.fromArea(area.getDouble());
        } else if (perimeter != null) {
            return Circle.fromPerimeter(perimeter.getDouble());
        } else {
            out.println("Not enough characteristics");
            return null;
        }
    }
}
