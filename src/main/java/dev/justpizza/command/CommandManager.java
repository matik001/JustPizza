package dev.justpizza.command;

import dev.justpizza.command.list.*;

import java.util.*;

public class CommandManager {
    final public static List<Command> commands = new ArrayList<>() {{
        add(new HelpCommand());
        add(new VersionCommand());
        add(new SquareCommand());
        add(new TriangleCommand());
        add(new ExitCommand());
        add(new RectangleCommand());
        add(new RhombusCommand());
    }};

    public Command getCommand(String commandName) {
        var command = commands.stream()
                              .filter(a -> a.name.equals(commandName))
                              .findAny();

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

            try {
                var command = getCommand(commandName);
                command.execute(Arrays.copyOfRange(arguments, 1, arguments.length));
            } catch (NoSuchElementException e) {
                System.out.println("Unknown command");
                getCommand("help").execute(new String[0]);
            }
        }
    }
}
