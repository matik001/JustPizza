package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.RegularHexagon;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.shape.triangle.EquilateralTriangle;
import dev.justpizza.translations.TranslationKey;

public class RegularHexagonCommand extends CreateShapeCommand {
    public static final String name = "reghexagon";
    public static final String description = AppSettings.global.translations.get(TranslationKey.regular_hexagon_description);

    public RegularHexagonCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("side"));
        argParser.addParamSchema(new ParamSchema("perimeter"));
        argParser.addParamSchema(new ParamSchema("area"));
        argParser.minNumberOfArgs = 1;
        argParser.maxNumberOfArgs = 1;
    }

    @Override
    protected Shape createShape(ShapesManager shapesManager, ArgParser argParser) {
        RegularHexagon hexagon;
        String argName = argParser.argValues.keySet().iterator().next();
        double value = argParser.getValue(argName).getDouble();
        try {
            switch (argName) {
                case "side" -> hexagon = RegularHexagon.fromSide(value);
                case "area" -> hexagon = RegularHexagon.fromArea(value);
                case "perimeter" -> hexagon = RegularHexagon.fromPerimeter(value);
                default -> {
                    assert false;
                    return null;
                }
            }
        } catch (IllegalShapeException e) {
            out.println(e.getMessage());
            return null;
        }
        return hexagon;
    }
}