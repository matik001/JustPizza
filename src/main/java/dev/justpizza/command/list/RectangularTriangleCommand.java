package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.Shape;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.shape.triangle.RectangularTriangle;
import dev.justpizza.translations.TranslationKey;

import java.util.List;
import java.util.Objects;

public class RectangularTriangleCommand extends CreateShapeCommand {

    public static final String name = "rectriangle";
    public static final String description =
            AppSettings.global.translations.get(TranslationKey.rectriangle_description);

    public RectangularTriangleCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("base"));
        argParser.addParamSchema(new ParamSchema("altitude"));
        argParser.addParamSchema(new ParamSchema("hypotenuse"));
        argParser.addParamSchema(new ParamSchema("area"));
        argParser.minNumberOfArgs = 2;
        argParser.maxNumberOfArgs = 2;
    }

    protected Shape createShape(ShapesManager shapesManager, ArgParser argParser) {
        var options = List.of("base", "altitude", "hypotenuse", "area");
        RectangularTriangle triangle = null;
        for (int i = 0; i < options.size(); i++) {
            for (int j = i + 1; j < options.size(); j++) {
                var key1 = options.get(i);
                var key2 = options.get(j);
                var _val1 = argParser.getValue(key1);
                var _val2 = argParser.getValue(key2);
                if (_val1 == null || _val2 == null)
                    continue;
                var val1 = _val1.getDouble();
                var val2 = _val2.getDouble();
                try {
                    if (Objects.equals(key1, "base") && Objects.equals(key2, "altitude"))
                        triangle = RectangularTriangle.fromBaseAltitude(val1, val2);
                    if (Objects.equals(key1, "base") && Objects.equals(key2, "hypotenuse"))
                        triangle = RectangularTriangle.fromBaseHypotenuse(val1, val2);
                    if (Objects.equals(key1, "base") && Objects.equals(key2, "area"))
                        triangle = RectangularTriangle.fromBaseArea(val1, val2);
                    if (Objects.equals(key1, "altitude") && Objects.equals(key2, "hypotenuse"))
                        triangle = RectangularTriangle.fromAltitudeHypotenuse(val2, val1);
                    if (Objects.equals(key1, "altitude") && Objects.equals(key2, "area"))
                        triangle = RectangularTriangle.fromAltitudeArea(val1, val2);
                    if (Objects.equals(key1, "hypotenuse") && Objects.equals(key2, "area"))
                        triangle = RectangularTriangle.fromHypotenuseArea(val1, val2);
                } catch (IllegalShapeException e) {
                    out.println(e.getMessage());
                    return null;
                }
            }
        }
        return triangle;
    }
}
