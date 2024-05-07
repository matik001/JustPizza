package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.argparser.ParamType;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.shape.triangle.EquilateralTriangle;
import dev.justpizza.shape.triangle.IsoscelesTriangle;
import dev.justpizza.shape.triangle.RectangularTriangle;
import dev.justpizza.shape.triangle.Triangle;
import dev.justpizza.shape.Shape;
import dev.justpizza.translations.TranslationKey;

import java.util.List;

public class TriangleCommand extends CreateShapeCommand {
    public static final String name = "triangle";
    public static final String description =
            AppSettings.global.translations.get(TranslationKey.triangle_description);

    public TriangleCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("sides", ParamType.POSITIVE_DOUBLE, false, true, 3));
        argParser.minNumberOfArgs = 1;
        argParser.maxNumberOfArgs = 1;
    }

    @Override
    protected Shape createShape(ShapesManager shapesManager, ArgParser argParser) {
        List<Double> sides = argParser.getValue("sides").getArray();
        Double sideA = sides.get(0);
        Double sideB = sides.get(1);
        Double sideC = sides.get(2);

        try {
            if (sideA.equals(sideB) && sideB.equals(sideC)) {
                return new EquilateralTriangle(sideA);
            } else if (sideA.equals(sideB)) {
                return IsoscelesTriangle.fromBaseSide(sideC, sideA);
            } else if (sideB.equals(sideC)) {
                return IsoscelesTriangle.fromBaseSide(sideA, sideB);
            } else if (sideA.equals(sideC)) {
                return IsoscelesTriangle.fromBaseSide(sideB, sideA);
            } else if ((sideA * sideA) + (sideB * sideB) == (sideC * sideC)){
                return new RectangularTriangle(sideA, sideB, sideC);
            } else {
                // You need to implement a method to create a general Triangle from three sides
                return new Triangle(sideA, sideB, sideC);
            }
        } catch (
                IllegalShapeException e) {
            out.println(e.getMessage());
            return null;
        }
    }
}