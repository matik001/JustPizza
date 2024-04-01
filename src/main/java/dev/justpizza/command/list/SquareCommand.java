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

    public SquareCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("side"));
        argParser.addParamSchema(new ParamSchema("diagonal"));
        argParser.addParamSchema(new ParamSchema("area"));
        argParser.minNumberOfArgs = 1;
        argParser.maxNumberOfArgs = 1;
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        Square square;
        String argName = argParser.argValues.keySet().iterator().next();
        double value = argParser.getValue(argName).getDouble();
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
