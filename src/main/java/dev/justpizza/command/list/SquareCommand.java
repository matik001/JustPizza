package dev.justpizza.command.list;

import dev.justpizza.command.Command;

public class SquareCommand extends Command {

    public static final String name = "square";

    public SquareCommand() {
        super(name, "calculates square characteristics");
    }

    @Override
    public void execute(String commandName, String[] params) {
        double side = 0;
        double diagonal = 0;
        double area = 0;
        double input = 0;
        String errorMessage = "Command usage: square [side | diagonal | area] {non-negative value}\n";
        if (params.length != 3) {
            System.out.print(errorMessage);
            return;
        }
        try{
            input = Double.parseDouble(params[2]);
            if(input < 0) {
                System.out.println(errorMessage);
                return;
            }
        }
        catch (NumberFormatException exc){
            System.out.println(errorMessage);
            return;
        }
        switch (params[1].toLowerCase()) {
            case "side" -> {
                side = input;
                diagonal = calculateDiagonal(side);
                area = calculateArea(side);
            }
            case "diagonal" -> {
                diagonal = input;
                side = calculateSide(diagonal);
                area = calculateArea(side);
            }
            case "area" -> {
                area = input;
                side = Math.sqrt(area);
                diagonal = calculateDiagonal(side);
            }
            default -> {
                System.out.println(errorMessage);
                return;
            }
        }
        System.out.print(printCharacteristic(side, diagonal, area));
    }

    public double calculateDiagonal(double side){
        return Math.sqrt(2) * side;
    }

    public double calculateArea(double side){
        return side * side;
    }

    public double calculateSide(double diagonal){
        return Math.sqrt(diagonal);
    }
    public String printCharacteristic(double side, double diagonal, double area){
        return (STR."Square characteristics:\n\tside: \{side}\n\tdiagonal: \{diagonal}\n\tarea: \{area}\n");
    }
}
