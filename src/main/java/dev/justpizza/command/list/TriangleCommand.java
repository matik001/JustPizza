package dev.justpizza.command.list;

import dev.justpizza.command.Command;

public class TriangleCommand extends Command {
    public static final String name = "triangle";

    public TriangleCommand() {
        super(name, "calculates triangle characteristics");
    }

    @Override
    public void execute(String commandName, String[] params) {
        double side = 0;
        double height = 0;
        double area = 0;
        String errorMessage = "Command usage: triangle [side | height | area] {non-negative value}\n";
        if (params.length != 3) {
            System.out.print(errorMessage);
            return;
        }
        if (Double.parseDouble(params[2]) < 0) {
            System.out.print(errorMessage);
            return;
        }
        switch (params[1].toLowerCase()) {
            case "side" -> {
                side = Double.parseDouble(params[2].toLowerCase());
                height = calculateHeight(side);
                area = calculateArea(side, height);
            }
            case "height" -> {
                height = Double.parseDouble(params[2].toLowerCase());
                side = calculateSideFromHeight(height);
                area = calculateArea(side, height);
            }
            case "area" -> {
                area = Double.parseDouble(params[2].toLowerCase());
                side = calculateSideFromArea(area);
                height = calculateHeight(side);
            }
            default -> System.out.println(errorMessage);
        }
        System.out.print(printCharacteristic(side, height, area));
    }

    public double calculateHeight(double side){
        return Math.sqrt(3) * side / 2;
    }

    public double calculateArea(double side, double height){
        return side * height * 0.5;
    }

    public double calculateSideFromHeight(double height){
        return height * 2 / Math.sqrt(3.0);
    }

    public double calculateSideFromArea(double area){
        return Math.sqrt(area * 4 / Math.sqrt(3));
    }

    public String printCharacteristic(double side, double height, double area){
        return (STR."Triangle characteristics:\n\tside: \{side}\n\theight: \{height}\n\tarea: \{area}\n");
    }
}
