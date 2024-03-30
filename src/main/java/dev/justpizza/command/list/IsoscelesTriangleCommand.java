package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.shape.EquilateralTriangle;
import dev.justpizza.shape.IsoscelesTriangle;
import dev.justpizza.shape.Shape;

import java.util.List;
import java.util.Objects;

public class IsoscelesTriangleCommand extends CreateShapeCommand {
    public static final String name = "isotriangle";
public static final String description = "calculates isosceles triangle characteristics";
    public IsoscelesTriangleCommand() {
        super(name, description);
    }
    @Override
    protected void initArgParser(ArgParser argParser) {

        argParser.possibleArgs.add(List.of("base", "side", "height", "area"));
        argParser.possibleArgs.add(List.of("base", "side", "height", "area"));
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        var options = List.of("base", "side", "height", "area");
        IsoscelesTriangle triangle = null;
        for (int i = 0; i < options.size(); i++) {
            for (int j = i+1; j < options.size(); j++) {
                var key1 = options.get(i);
                var key2 = options.get(j);
                var val1 = argParser.argValues.get(key1);
                var val2 = argParser.argValues.get(key2);
                if(val1 == null || val2 == null)
                    continue;
                if(Objects.equals(key1, "base") && Objects.equals(key2, "side"))
                    triangle = IsoscelesTriangle.fromBaseSide(val1, val2);
                if(Objects.equals(key1, "base") && Objects.equals(key2, "height"))
                    triangle = IsoscelesTriangle.fromBaseHeight(val1, val2);
                if(Objects.equals(key1, "base") && Objects.equals(key2, "area"))
                    triangle = IsoscelesTriangle.fromBaseArea(val1, val2);
                if(Objects.equals(key1, "side") && Objects.equals(key2, "height"))
                    triangle = IsoscelesTriangle.fromHeightSide(val2, val1);
                if(Objects.equals(key1, "side") && Objects.equals(key2, "area"))
                    triangle = IsoscelesTriangle.fromSideArea(val1, val2);
                if(Objects.equals(key1, "height") && Objects.equals(key2, "area"))
                    triangle = IsoscelesTriangle.fromHeightArea(val1, val2);
            }
        }
        return triangle;
    }


}
