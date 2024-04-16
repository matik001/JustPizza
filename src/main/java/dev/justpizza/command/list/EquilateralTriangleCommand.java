package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.EquilateralTriangle;
import dev.justpizza.shape.Shape;
import dev.justpizza.translations.TranslationKey;

import java.util.List;

public class EquilateralTriangleCommand extends CreateShapeCommand {
    public static final String name = "equtriangle";
    public static final String description = AppSettings.global.translations.get(TranslationKey.equtriangle_description);

    public static final int minNumberOfArgs = 2;
    public static final int maxNumberOfArgs = 2;
    public EquilateralTriangleCommand() {
        super(name, description, minNumberOfArgs, maxNumberOfArgs);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.paramsSchemaList.add(new ParamSchema("side"));
        argParser.paramsSchemaList.add(new ParamSchema("height"));
        argParser.paramsSchemaList.add(new ParamSchema("area"));
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        EquilateralTriangle triangle;
        String argName = argParser.argValues.keySet().iterator().next();
        Double value = argParser.argValues.get(argName).getDouble();
        switch (argName) {
            case "side" -> triangle = EquilateralTriangle.fromSide(value);
            case "height" -> triangle = EquilateralTriangle.fromHeight(value);
            case "area" -> triangle = EquilateralTriangle.fromArea(value);
            default -> {
                assert false;
                return null;
            }
        }
        return triangle;
    }
}
