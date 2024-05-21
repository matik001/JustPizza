package dev.justpizza.command.list.ellipse;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.shape.ellipse.Circle;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.ellipse.Ellipse;
import dev.justpizza.translations.TranslationKey;

public class EllipseCommand extends CreateShapeCommand {
    private static final String name = "ellipse";

    public EllipseCommand() {
        super(name);
    }
    @Override
    public String getDescription() {
        return AppSettings.global.translations.get(TranslationKey.ellipse_description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("semiMajorAxis", true));
        argParser.addParamSchema(new ParamSchema("semiMinorAxis", true));
        argParser.addParamSchema(new ParamSchema("area", true));
        argParser.minNumberOfArgs = 2;
        argParser.maxNumberOfArgs = 2;
    }

    @Override
    protected Shape createShape(ShapesManager shapesManager, ArgParser argParser) {
        var semiMajorAxis = argParser.getValue("semiMajorAxis");
        var semiMinorAxis = argParser.getValue("semiMinorAxis");
        var area = argParser.getValue("area");

        if (area == null) {
            try {
                return Ellipse.fromSemiMajorMinor(semiMajorAxis.getDouble(), semiMinorAxis.getDouble());
            } catch (IllegalShapeException e) {
                out.println(e.getMessage());
                return null;
            }
        } else {
            double semiAxis = semiMajorAxis != null ? semiMajorAxis.getDouble() : semiMinorAxis.getDouble();
            try {
                return Ellipse.fromSemiAxisAndArea(semiAxis, area.getDouble());
            } catch (IllegalShapeException e) {
                out.println(e.getMessage());
                return null;
            }
        }
    }
}
