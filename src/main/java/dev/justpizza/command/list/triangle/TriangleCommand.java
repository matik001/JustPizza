package dev.justpizza.command.list.triangle;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.argparser.ParamType;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.shape.triangle.Triangle;
import dev.justpizza.translations.TranslationKey;

import java.util.List;

public class TriangleCommand extends CreateShapeCommand {
    public static final String name = "triangle";

    public TriangleCommand() {
        super(name);
    }
    @Override
    public String getDescription() {
        return AppSettings.global.translations.get(TranslationKey.triangle_description);
    }
    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("sides", ParamType.POSITIVE_DOUBLE, false, true, 3));
        argParser.minNumberOfArgs = 1;
        argParser.maxNumberOfArgs = 1;
    }

    @Override
    protected Shape createShape(ShapesManager shapesManager, ArgParser argParser) {
        List<Double> sides = argParser.getValue("sides").getArray();
        Double sideA = sides.get(0);
        Double sideB = sides.get(1);
        Double sideC = sides.get(2);

        try {
            return Triangle.fromSides(sideA, sideB, sideC);
        } catch (IllegalShapeException e) {
            out.println(e.getMessage());
            return null;
        }
    }
}