package dev.justpizza.command.list;

import dev.justpizza.command.Command;

public class SquareCommand extends Command {

    public static final String name = "square";

    public SquareCommand() {
        super(name, "calculates square characteristics");
    }

    private double side;
    private double diagonal;
    private double area;


    @Override
    public void execute(String commandName, String[] params) {
        String errorMessage = "Command usage: square [side | diagonal | area] {non-negative value}\n";
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
                diagonal = Math.sqrt(2) * side;
                area = side * side;
            }
            case "diagonal" -> {
                diagonal = Double.parseDouble(params[2].toLowerCase());
                side = diagonal / Math.sqrt(2);
                area = side * side;
            }
            case "area" -> {
                area = Double.parseDouble(params[2].toLowerCase());
                side = Math.sqrt(area);
                diagonal = Math.sqrt(2) * side;
            }
            default -> System.out.println(errorMessage);
        }
        System.out.print(this);
    }

    public String toString(){
        return (STR."Square characteristics:\n\tside: \{side}\n\tdiagonal: \{diagonal}\n\tarea: \{area}\n");
    }
}
