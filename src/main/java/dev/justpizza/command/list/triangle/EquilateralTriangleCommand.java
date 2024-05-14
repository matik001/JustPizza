package dev.justpizza.command.list.triangle;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.shape.triangle.EquilateralTriangle;
import dev.justpizza.shape.Shape;
import dev.justpizza.translations.TranslationKey;

public class EquilateralTriangleCommand extends CreateShapeCommand {
    public static final String name = "equtriangle";
    public static final String description = AppSettings.global.translations.get(TranslationKey.equtriangle_description);

    public EquilateralTriangleCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("side"));
        argParser.addParamSchema(new ParamSchema("height"));
        argParser.addParamSchema(new ParamSchema("area"));
        argParser.minNumberOfArgs = 1;
        argParser.maxNumberOfArgs = 1;
    }

    @Override
    protected Shape createShape(ShapesManager shapesManager, ArgParser argParser) {
        EquilateralTriangle triangle;
        String argName = argParser.argValues.keySet().iterator().next();
        double value = argParser.getValue(argName).getDouble();
        try {
            switch (argName) {
                case "side" -> triangle = EquilateralTriangle.fromSide(value);
                case "height" -> triangle = EquilateralTriangle.fromHeight(value);
                case "area" -> triangle = EquilateralTriangle.fromArea(value);
                default -> {
                    assert false;
                    return null;
                }
            }
        } catch (IllegalShapeException e) {
            out.println(e.getMessage());
            return null;
        }
        return triangle;
    }
}
