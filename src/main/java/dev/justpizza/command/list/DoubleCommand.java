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

public class DoubleCommand extends CreateShapeCommand {
    public static final String name = "double";
    public static final String description = AppSettings.global.translations.get(TranslationKey.double_description);

    public DoubleCommand(){ super(name, description); }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("shapenumber", ParamType.INT));
        argParser.minNumberOfArgs = 1;
        argParser.maxNumberOfArgs = 1;
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        var shapeNumber = argParser.getValue("shapenumber").getInt();
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
        return shape.doubleArea();
    }
}