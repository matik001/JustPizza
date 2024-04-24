package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.argparser.ParamType;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.triangle.RectangularTriangle;
import dev.justpizza.translations.TranslationKey;

import java.util.List;

public class RectangularTriangleCommand extends CreateShapeCommand {

    public static final String name = "rectriangle";
    public static final String description =
            AppSettings.global.translations.get(TranslationKey.triangle_description);

    public RectangularTriangleCommand() {
        super(name, description);
    }
    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("sides", ParamType.POSITIVE_DOUBLE, false, true, 3));
        argParser.minNumberOfArgs = 1;
        argParser.maxNumberOfArgs = 1;
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        List<Double> sides = argParser.getValue("sides").getArray();
        Double sideA = sides.get(0);
        Double sideB = sides.get(1);
        Double sideC = sides.get(2);
        try {
            return new RectangularTriangle(sideA, sideB, sideC);
        } catch (IllegalShapeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
