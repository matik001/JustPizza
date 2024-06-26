package dev.justpizza.command.list.quadrangle;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.*;
import dev.justpizza.shape.quadrangle.Rectangle;
import dev.justpizza.translations.TranslationKey;

public class RectangleCommand extends CreateShapeCommand {
    public static final String name = "rectangle";
    public RectangleCommand() {
        super(name);
    }
    @Override
    public String getDescription() {
        return AppSettings.global.translations.get(TranslationKey.rectangle_description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("sidea"));
        argParser.addParamSchema(new ParamSchema("sideb"));
        argParser.addParamSchema(new ParamSchema("diagonal"));
        argParser.addParamSchema(new ParamSchema("area"));
        argParser.minNumberOfArgs = 2;
        argParser.maxNumberOfArgs = 2;
    }

    @Override
    protected Shape createShape(ShapesManager shapesManager, ArgParser argParser) {
        Shape shape;
        var sideA = argParser.getValue("sidea");
        var sideB = argParser.getValue("sideb");
        var side = sideA != null ? sideA : sideB;
        var diagonal = argParser.getValue("diagonal");
        var area = argParser.getValue("area");

        if (sideA != null && sideB != null) {
            shape = Rectangle.fromSides(sideA.getDouble(), sideB.getDouble());
        } else if (side != null && diagonal != null) {
            shape = Rectangle.fromDiagonalAndSide(diagonal.getDouble(), side.getDouble());
        } else if (side != null && area != null) {
            shape = Rectangle.fromAreaAndSide(area.getDouble(), side.getDouble());
        } else if (diagonal != null && area != null) {
            try {
                shape = Rectangle.fromDiagonalAndArea(diagonal.getDouble(), area.getDouble());
            } catch (IllegalShapeException e) {
                out.println(e.getMessage());
                return null;
            }
        } else {
            out.println("Not enough characteristics");
            return null;
        }
        return shape;
    }

}