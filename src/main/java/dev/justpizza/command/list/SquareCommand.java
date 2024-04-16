package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.quadrangle.Square;
import dev.justpizza.translations.TranslationKey;

import java.util.List;

public class SquareCommand extends CreateShapeCommand {

    public static final String name = "square";
    public static final String description = AppSettings.global.translations.get(TranslationKey.square_description);

    public static final int minNumberOfArgs = 1;
    public static final int maxNumberOfArgs = 1;

    public SquareCommand() {
        super(name, description, minNumberOfArgs, maxNumberOfArgs);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.paramsSchemaList.add(new ParamSchema("side"));
        argParser.paramsSchemaList.add(new ParamSchema("diagonal"));
        argParser.paramsSchemaList.add(new ParamSchema("area"));
        argParser.minNumberOfArgs = minNumberOfArgs;
        argParser.maxNumberOfArgs = maxNumberOfArgs;
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        Square square;
        String argName = argParser.argValues.keySet().iterator().next();
        double value = argParser.argValues.get(argName).getDouble();
        switch (argName) {
            case "side" -> square = Square.fromSide(value);
            case "diagonal" -> square = Square.fromDiagonal(value);
            case "area" -> square = Square.fromArea(value);
            default -> {
                assert false;
                return null;
            }
        }
        return square;
    }
}
