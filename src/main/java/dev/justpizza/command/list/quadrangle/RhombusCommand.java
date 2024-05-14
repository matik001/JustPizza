package dev.justpizza.command.list.quadrangle;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.shape.quadrangle.Rhombus;
import dev.justpizza.shape.Shape;
import dev.justpizza.translations.TranslationKey;

public class RhombusCommand extends CreateShapeCommand {
    public static final String name = "rhombus";
    public static final String description = AppSettings.global.translations.get(TranslationKey.rhombus_description);

    public RhombusCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("side"));
        argParser.addParamSchema(new ParamSchema("diagonala"));
        argParser.addParamSchema(new ParamSchema("diagonalb"));
        argParser.addParamSchema(new ParamSchema("area"));
        argParser.minNumberOfArgs = 2;
        argParser.maxNumberOfArgs = 2;
    }

    @Override
    protected Shape createShape(ShapesManager shapesManager, ArgParser argParser) {
        Shape shape;
        var side = argParser.getValue("side");
        var diagonala = argParser.getValue("diagonala");
        var diagonalb = argParser.getValue("diagonalb");
        var diagonal = diagonala != null ? diagonala : diagonalb;
        var area = argParser.getValue("area");


        if (diagonala != null && diagonalb != null) {
            shape = Rhombus.fromDiagonals(diagonala.getDouble(), diagonalb.getDouble());
        } else if (side != null && area != null) {
            try {
                shape = Rhombus.fromSideAndArea(side.getDouble(), area.getDouble());
            } catch (IllegalShapeException e) {
                out.println(e.getMessage());
                return null;
            }
        } else if (side != null && diagonal != null) {
            shape = Rhombus.fromSideAndDiagonal(side.getDouble(), diagonal.getDouble());
        } else if (area != null && diagonal != null) {
            shape = Rhombus.fromDiagonalAndArea(diagonal.getDouble(), area.getDouble());
        } else {
            out.println(AppSettings.global.translations.get(TranslationKey.not_enough_characteristics));
            return null;
        }
        return shape;
    }
}
