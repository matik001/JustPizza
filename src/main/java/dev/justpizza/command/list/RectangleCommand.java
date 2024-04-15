package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.*;
import dev.justpizza.shape.quadrangle.Rectangle;
import dev.justpizza.translations.TranslationKey;

import java.util.List;

public class RectangleCommand extends CreateShapeCommand {
    public static final String name = "rectangle";
    public static final String description = AppSettings.global.translations.get(TranslationKey.rectangle_description);

    public RectangleCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.paramsSchemaList.add(List.of(
                new ParamSchema("sidea"), new ParamSchema("sideb"),
                new ParamSchema("diagonal"), new ParamSchema("area")));
        argParser.paramsSchemaList.add(List.of(
                new ParamSchema("sidea"), new ParamSchema("sideb"),
                new ParamSchema("diagonal"), new ParamSchema("area")));

    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        Shape shape;
        var sideA = argParser.argValues.get("sidea");
        var sideB = argParser.argValues.get("sideb");
        var side = sideA != null ? sideA : sideB;
        var diagonal = argParser.argValues.get("diagonal");
        var area = argParser.argValues.get("area");

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
                System.out.println(e.getMessage());
                return null;
            }
        } else {
            System.out.println("Not enough characteristics");
            return null;
        }
        return shape;
    }

}