package dev.justpizza.command.list;

import dev.justpizza.command.Command;

public class TriangleCommand extends Command {
    public static final String name = "triangle";

    public TriangleCommand() {
        super(name, "calculates triangle characteristics");
    }

    public double side;
    private double height;
    private double area;
    @Override
    public void execute(String commandName, String[] params) {
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
                height = Math.sqrt(3) * side / 2;
                area = side * height * 0.5;
            }
            case "height" -> {
                height = Double.parseDouble(params[2].toLowerCase());
                side = height * 2 / Math.sqrt(3.0);
                area = side * height * 0.5;
            }
            case "area" -> {
                area = Double.parseDouble(params[2].toLowerCase());
                side = Math.sqrt(area * 4 / Math.sqrt(3));
                height = Math.sqrt(3) * side / 2;
            }
            default -> System.out.println(errorMessage);
        }
        System.out.print(this);
    }

    public String toString(){
        return (STR."Triangle characteristics:\n\tside: \{side}\n\theight: \{height}\n\tarea: \{area}\n");
    }
}
