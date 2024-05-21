package dev.justpizza.command.list.triangle;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.shape.triangle.IsoscelesTriangle;
import dev.justpizza.translations.TranslationKey;

public class IsoscelesTriangleCommand extends CreateShapeCommand {
    public static final String name = "isotriangle";
    public static final String description = AppSettings.global.translations.get(TranslationKey.isosceles_description);

    public IsoscelesTriangleCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("base"));
        argParser.addParamSchema(new ParamSchema("side"));
        argParser.addParamSchema(new ParamSchema("height"));
        argParser.addParamSchema(new ParamSchema("area"));
        argParser.minNumberOfArgs = 2;
        argParser.maxNumberOfArgs = 2;
    }

    @Override
    protected Shape createShape(ShapesManager shapesManager, ArgParser argParser) {
        var base = argParser.getValue("base");
        var side = argParser.getValue("side");
        var height = argParser.getValue("height");
        var area = argParser.getValue("area");

        try {
            if (base != null && side != null)
                return IsoscelesTriangle.fromBaseSide(base.getDouble(), side.getDouble());
            if (base != null && height != null)
                return IsoscelesTriangle.fromBaseHeight(base.getDouble(), height.getDouble());
            if (base != null && area != null)
                return IsoscelesTriangle.fromBaseArea(base.getDouble(), area.getDouble());
            if (side != null && height != null)
                return IsoscelesTriangle.fromHeightSide(height.getDouble(), side.getDouble());
            if (side != null && area != null)
                return IsoscelesTriangle.fromSideArea(side.getDouble(), area.getDouble());
            if (height != null && area != null)
                return IsoscelesTriangle.fromHeightArea(height.getDouble(), area.getDouble());
        } catch (IllegalShapeException e) {
            out.println(e.getMessage());
            return null;
        }
        return null;
    }
}
