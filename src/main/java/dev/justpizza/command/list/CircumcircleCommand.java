package dev.justpizza.command.list;

import dev.justpizza.command.Command;
import dev.justpizza.shape.ShapesManager;

public class CircumcircleCommand extends Command {
    public static final String name = "circumcircle";
    public static final String description = "creates circumcircle";

    public CircumcircleCommand() {
        super(name, description);
    }

    @Override
    public void execute(String[] params) {
        var i = Integer.parseInt(params[0]);
        var shape = ShapesManager.instance.get(i);
        var circumcircle = shape.createCircumcircle();
        ShapesManager.instance.addShape(circumcircle);
        circumcircle.printCharacteristic();
    }
}
