package dev.justpizza.command.list.quadrangle;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.shape.quadrangle.IsoscelesTrapezium;
import dev.justpizza.translations.TranslationKey;

public class IsoscelesTrapeziumCommand extends CreateShapeCommand {
    public static final String name = "isotrapezium";
    public IsoscelesTrapeziumCommand() {
        super(name);
    }
    @Override
    public String getDescription() {
        return AppSettings.global.translations.get(TranslationKey.isosceles_trapezium_description);
    }
    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("basea"));
        argParser.addParamSchema(new ParamSchema("baseb"));
        argParser.addParamSchema(new ParamSchema("height"));
        argParser.addParamSchema(new ParamSchema("leg"));
        argParser.addParamSchema(new ParamSchema("area"));
        argParser.minNumberOfArgs = 3;
        argParser.maxNumberOfArgs = 3;
    }

    @Override
    protected Shape createShape(ShapesManager shapesManager, ArgParser argParser) {
        var basea = argParser.getValue("basea");
        var baseb = argParser.getValue("baseb");
        var base = basea != null ? basea : baseb;
        var height = argParser.getValue("height");
        var leg = argParser.getValue("leg");
        var area = argParser.getValue("area");
        try {
            if (basea != null && baseb != null && height != null) {
                return IsoscelesTrapezium.fromBasesAndHeight(basea.getDouble(), baseb.getDouble(), height.getDouble());
            } else if (basea != null && baseb != null && leg != null) {
                return IsoscelesTrapezium.fromBasesAndLeg(basea.getDouble(), baseb.getDouble(), leg.getDouble());
            } else if (basea != null && baseb != null && area != null) {
                return IsoscelesTrapezium.fromBasesAndArea(basea.getDouble(), baseb.getDouble(), area.getDouble());
            } else if (basea != null && height != null && leg != null) {
                return IsoscelesTrapezium.fromBaseAAndHeightAndLeg(basea.getDouble(), height.getDouble(), leg.getDouble());
            } else if (baseb != null && height != null && leg != null) {
                return IsoscelesTrapezium.fromBaseBAndHeightAndLeg(baseb.getDouble(), height.getDouble(), leg.getDouble());
            } else if (base != null && height != null && area != null) {
                return IsoscelesTrapezium.fromBaseAndHeightAndArea(base.getDouble(), height.getDouble(), area.getDouble());
            } else if (height != null && leg != null && area != null) {
                return IsoscelesTrapezium.fromHeightAndLegAndArea(height.getDouble(), leg.getDouble(), area.getDouble());
            } else if (base != null && leg != null && area != null) {
                return IsoscelesTrapezium.fromBaseAndLegAndArea(base.getDouble(), leg.getDouble(), area.getDouble());
            }
        } catch (IllegalShapeException e) {
            out.println(e.getMessage());
            return null;
        }
        return null;
    }
}
