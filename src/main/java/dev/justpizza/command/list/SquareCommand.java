package dev.justpizza.command.list;

import dev.justpizza.command.Command;
import dev.justpizza.shape.Square;

public class SquareCommand extends Command {

    public static final String name = "square";

    public SquareCommand() {
        super(name, "calculates square characteristics");
    }

    @Override
    public void execute(String commandName, String[] params) {
        double input;
        String errorMessage = "Command usage: square [side | diagonal | area] {non-negative value}\n";
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
        Square square;
        switch (params[1].toLowerCase()) {
            case "side" -> square = Square.fromSide(input);
            case "diagonal" -> square = Square.fromDiagonal(input);
            case "area" -> square = Square.fromArea(input);
            default -> {
                System.out.println(errorMessage);
                return;
            }
        }
        square.printCharacteristic();
    }
}
