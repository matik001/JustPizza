package dev.justpizza.command.list.ellipse;

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
    public CircumcircleCommand() {
        super(name);
    }
    @Override
    public String getDescription() {
        return AppSettings.global.translations.get(TranslationKey.circumcircle_description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("shapenumber", ParamType.INT));
        argParser.minNumberOfArgs = 1;
        argParser.maxNumberOfArgs = 1;
    }

    @Override
    protected Shape createShape(ShapesManager shapesManager, ArgParser argParser) {
        var shapeNumber = argParser.getValue("shapenumber").getInt();
        var shapesManagerSize = shapesManager.size();
        if (shapesManagerSize == 0) {
            out.println(AppSettings.global.translations.get(TranslationKey.zero_shapes_stored));
            return null;
        }

        if (shapeNumber < 1 || shapesManagerSize < shapeNumber) {
            var notInRangeShapesStored = AppSettings.global.translations.get(TranslationKey.not_in_range_shapes_stored);
            out.println(notInRangeShapesStored.replace("{shapesManagerSize}", Integer.toString(shapesManagerSize)));
            return null;
        }

        var shape = shapesManager.get(shapeNumber - 1);
        try {
            return shape.createCircumcircle();
        } catch (IllegalShapeException e) {
            out.println(e.getMessage());
            return null;
        }
    }
}
