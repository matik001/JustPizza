package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.triangle.EquilateralTriangle;
import dev.justpizza.shape.triangle.IsoscelesTriangle;
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
        argParser.paramsSchemaList.add(
                List.of(
                        new ParamSchema("sidea"),
                        new ParamSchema("sideb"),
                        new ParamSchema("sidec")
                )
        );
        argParser.paramsSchemaList.add(
                List.of(
                        new ParamSchema("sidea"),
                        new ParamSchema("sideb"),
                        new ParamSchema("sidec")
                )
        );
        argParser.paramsSchemaList.add(
                List.of(
                        new ParamSchema("sidea"),
                        new ParamSchema("sideb"),
                        new ParamSchema("sidec")
                )
        );
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        Double sideA = argParser.argValues.get("sidea").getDouble();
        Double sideB = argParser.argValues.get("sideb").getDouble();
        Double sideC = argParser.argValues.get("sidec").getDouble();

        try {
            if (sideA.equals(sideB) && sideB.equals(sideC)) {
                return new EquilateralTriangle(sideA);
            } else if (sideA.equals(sideB)) {
                return IsoscelesTriangle.fromBaseSide(sideC, sideA);
            } else if (sideB.equals(sideC)) {
                return IsoscelesTriangle.fromBaseSide(sideA, sideB);
            } else if (sideA.equals(sideC)) {
                return IsoscelesTriangle.fromBaseSide(sideB, sideA);
            } else {
                // You need to implement a method to create a general Triangle from three sides
                return new Triangle(sideA, sideB, sideC);
            }
        } catch (
                IllegalShapeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}