package dev.justpizza.command;

import dev.justpizza.command.list.HelpCommand;
import dev.justpizza.command.list.ExitCommand;
import dev.justpizza.command.list.SquareCommand;
import dev.justpizza.command.list.TriangleCommand;
import dev.justpizza.command.list.VersionCommand;

import java.util.*;

public class CommandManager {
    final public static List<Command> commands = new ArrayList<>() {{
        add(new HelpCommand());
        add(new VersionCommand());
        add(new SquareCommand());
        add(new TriangleCommand());
        add(new ExitCommand());
    }};

    public Command getCommand(String commandName) {
        var command = commands.stream()
                .filter(a -> a.name.equals(commandName))
                .findAny();

        if (command.isEmpty())
            command = Optional.of(getCommand("help"));
        return command.orElseThrow(); /// throw should not happen
    }

    public void run() {
        var input = new Scanner(System.in);

        while (input.hasNext()) {
            var line = input.nextLine();
            var arguments = line.split("\\s+");
            if (arguments.length == 0) {
                continue;
            }

            var commandName = arguments[0].toLowerCase();

            var command = getCommand(commandName);
            if (command == null) {
                System.out.println("Unknown command");
                getCommand("help").execute(new String[0]);
                continue;
            }
            command.execute(Arrays.copyOfRange(arguments, 1, arguments.length));
        }
    }
}
