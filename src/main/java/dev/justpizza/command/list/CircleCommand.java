package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.circle.Circle;
import dev.justpizza.shape.Shape;
import dev.justpizza.translations.TranslationKey;
import dev.justpizza.utils.Utils;

import java.util.List;

public class CircleCommand extends CreateShapeCommand {
    private static final String name = "circle";
    private static final String description = AppSettings.global.translations.get(TranslationKey.circle_description);

    public CircleCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.paramsSchemaList.add(List.of(
                new ParamSchema("radius"), new ParamSchema("area"),
                new ParamSchema("perimeter")));
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        var radius = argParser.argValues.get("radius");
        var area = argParser.argValues.get("area");
        var perimeter = argParser.argValues.get("perimeter");

        if (radius != null) {
            return Circle.fromRadius(radius.getDouble());
        } else if (area != null) {
            return Circle.fromArea(area.getDouble());
        } else if (perimeter != null) {
            return Circle.fromPerimeter(perimeter.getDouble());
        } else {
            System.out.println("Not enough characteristics");
            return null;
        }
    }
}
