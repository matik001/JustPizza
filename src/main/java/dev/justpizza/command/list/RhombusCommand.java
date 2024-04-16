package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.quadrangle.Rhombus;
import dev.justpizza.shape.Shape;
import dev.justpizza.translations.TranslationKey;

import java.util.List;

public class RhombusCommand extends CreateShapeCommand {
    public static final String name = "rhombus";
    public static final String description = AppSettings.global.translations.get(TranslationKey.rhombus_description);

    public static final int minNumberOfArgs = 2;
    public static final int maxNumberOfArgs = 2;
    public RhombusCommand() {
        super(name, description, minNumberOfArgs, maxNumberOfArgs);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.paramsSchemaList.add(new ParamSchema("side"));
        argParser.paramsSchemaList.add(new ParamSchema("diagonala"));
        argParser.paramsSchemaList.add(new ParamSchema("diagonalb"));
        argParser.paramsSchemaList.add(new ParamSchema("area"));
        argParser.minNumberOfArgs = minNumberOfArgs;
        argParser.maxNumberOfArgs = maxNumberOfArgs;
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        Shape shape;
        var side = argParser.argValues.get("side");
        var diagonala = argParser.argValues.get("diagonala");
        var diagonalb = argParser.argValues.get("diagonalb");
        var diagonal = diagonala != null ? diagonala : diagonalb;
        var area = argParser.argValues.get("area");



        if (diagonala != null && diagonalb != null) {
            shape = Rhombus.fromDiagonals(diagonala.getDouble(), diagonalb.getDouble());
        } else if (side != null && area != null) {
            try {
                shape = Rhombus.fromSideAndArea(side.getDouble(), area.getDouble());
            } catch (IllegalShapeException e) {
                System.out.println(e.getMessage());
                return null;
            }
        } else if (side != null && diagonal != null) {
            shape = Rhombus.fromSideAndDiagonal(side.getDouble(), diagonal.getDouble());
        } else if (area != null && diagonal != null) {
            shape = Rhombus.fromDiagonalAndArea(diagonal.getDouble(), area.getDouble());
        } else {
            System.out.println(AppSettings.global.translations.get(TranslationKey.not_enough_characteristics));
            return null;
        }
        return shape;
    }
}
