package dev.justpizza;

import command.Command;
import command.HelpCommand;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var input = new Scanner(System.in);

        var commands = new ArrayList<Command>();
        commands.add(new HelpCommand());

        while (input.hasNext()) {
            var line = input.nextLine();
            var arguments = line.split("\\s+");

            // Assuming that at most 1 command matches input line.
            var command = commands.stream()
                                  .filter(cmd -> cmd.matchesInput(arguments[0]))
                                  .findAny();

            if (command.isPresent()) {
                command.get().execute(arguments);
            } else {
                System.out.println("Unknown command");
            }
        }
    }
}