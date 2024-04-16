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

public class CircumcircleCommand extends CreateShapeCommand {
    public static final String name = "circumcircle";
    public static final String description = AppSettings.global.translations.get(TranslationKey.circumcircle_description);

    public static final int minNumberOfArgs = 1;
    public static final int maxNumberOfArgs = 1;
    public CircumcircleCommand() {
        super(name, description, minNumberOfArgs, maxNumberOfArgs);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.paramsSchemaList.add(new ParamSchema("shapenumber", ParamType.INT));
        argParser.minNumberOfArgs = minNumberOfArgs;
        argParser.maxNumberOfArgs = maxNumberOfArgs;
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
