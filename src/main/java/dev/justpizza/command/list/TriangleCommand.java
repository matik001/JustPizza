package dev.justpizza.command.list;

import dev.justpizza.command.Command;
import dev.justpizza.shape.EquilateralTriangle;

public class TriangleCommand extends Command {
    public static final String name = "triangle";

    public TriangleCommand() {
        super(name, "calculates triangle characteristics");
    }

    @Override
    public void execute(String commandName, String[] params) {
        double input;
        String errorMessage = "Command usage: triangle [side | height | area] {non-negative value}\n";
        if (params.length != 3) {
            System.out.print(errorMessage);
            return;
        }

        try {
            input = Double.parseDouble(params[2]);
            if (input < 0) {
                System.out.println(errorMessage);
                return;
            }
        } catch (NumberFormatException exc) {
            System.out.println(errorMessage);
            return;
        }
        EquilateralTriangle triangle;
        switch (params[1].toLowerCase()) {
            case "side" -> triangle = EquilateralTriangle.fromSide(input);
            case "height" -> triangle = EquilateralTriangle.fromHeight(input);
            case "area" -> triangle = EquilateralTriangle.fromArea(input);
            default -> {
                System.out.println(errorMessage);
                return;
            }
        }
        triangle.printCharacteristic();
    }
}
