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

    public RhombusCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.paramsSchemaList.add(List.of(
                new ParamSchema("side"), new ParamSchema("diagonala"),
                new ParamSchema("diagonalb"), new ParamSchema("area")));
        argParser.paramsSchemaList.add(List.of(
                new ParamSchema("side"), new ParamSchema("diagonala"),
                new ParamSchema("diagonalb"), new ParamSchema("area")));
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
