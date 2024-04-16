package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.IsoscelesTriangle;
import dev.justpizza.shape.Shape;
import dev.justpizza.translations.TranslationKey;

import java.util.List;
import java.util.Objects;

public class IsoscelesTriangleCommand extends CreateShapeCommand {
    public static final String name = "isotriangle";
    public static final String description = AppSettings.global.translations.get(TranslationKey.isosceles_description);

    public static final int minNumberOfArgs = 2;
    public static final int maxNumberOfArgs = 2;
    public IsoscelesTriangleCommand() {
        super(name, description, minNumberOfArgs, maxNumberOfArgs);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.paramsSchemaList.add(new ParamSchema("base"));
        argParser.paramsSchemaList.add(new ParamSchema("side"));
        argParser.paramsSchemaList.add(new ParamSchema("height"));
        argParser.paramsSchemaList.add(new ParamSchema("area"));
        argParser.minNumberOfArgs = minNumberOfArgs;
        argParser.maxNumberOfArgs = maxNumberOfArgs;
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        var options = List.of("base", "side", "height", "area");
        IsoscelesTriangle triangle = null;
        for (int i = 0; i < options.size(); i++) {
            for (int j = i + 1; j < options.size(); j++) {
                var key1 = options.get(i);
                var key2 = options.get(j);
                var _val1 = argParser.argValues.get(key1);
                var _val2 = argParser.argValues.get(key2);
                if (_val1 == null || _val2 == null)
                    continue;
                var val1 = _val1.getDouble();
                var val2 = _val2.getDouble();
                try {
                    if (Objects.equals(key1, "base") && Objects.equals(key2, "side"))
                        triangle = IsoscelesTriangle.fromBaseSide(val1, val2);
                    if (Objects.equals(key1, "base") && Objects.equals(key2, "height"))
                        triangle = IsoscelesTriangle.fromBaseHeight(val1, val2);
                    if (Objects.equals(key1, "base") && Objects.equals(key2, "area"))
                        triangle = IsoscelesTriangle.fromBaseArea(val1, val2);
                    if (Objects.equals(key1, "side") && Objects.equals(key2, "height"))
                        triangle = IsoscelesTriangle.fromHeightSide(val2, val1);
                    if (Objects.equals(key1, "side") && Objects.equals(key2, "area"))
                        triangle = IsoscelesTriangle.fromSideArea(val1, val2);
                    if (Objects.equals(key1, "height") && Objects.equals(key2, "area"))
                        triangle = IsoscelesTriangle.fromHeightArea(val1, val2);
                } catch (IllegalShapeException e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }
        }
        return triangle;
    }
}
