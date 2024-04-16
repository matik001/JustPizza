package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.argparser.ParamType;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.translations.TranslationKey;
import dev.justpizza.utils.Utils;

import java.util.List;

public class CircumcircleCommand extends CreateShapeCommand {
    public static final String name = "circumcircle";
    public static final String description =
            AppSettings.global.translations.get(TranslationKey.circumcircle_description);

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
            System.out.println(AppSettings.global.translations.get(TranslationKey.zero_shapes_stored));
            return null;
        }

        if (shapeNumber < 1 || shapesManagerSize < shapeNumber) {
            var notInRangeShapesStored = AppSettings.global.translations.get(TranslationKey.not_in_range_shapes_stored);
            System.out.println(notInRangeShapesStored.replace("{shapesManagerSize}", Integer.toString(shapesManagerSize)));
            return null;
        }

        var shape = ShapesManager.instance.get(shapeNumber - 1);
        try {
            return shape.createCircumcircle();
        } catch (IllegalShapeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
