package dev.justpizza.command;

import dev.justpizza.command.list.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommandManager {
    final private static List<Command> commandsList = new ArrayList<>() {{
        add(new HelpCommand());
        add(new VersionCommand());
        add(new SquareCommand());
        add(new TriangleCommand());
        add(new ExitCommand());
        add(new RectangleCommand());
        add(new RhombusCommand());
    }};

    final public static Map<String, Command> commands =
            commandsList.stream().collect(Collectors.toMap(Command::getName, Function.identity()));

    public void run() {
        var input = new Scanner(System.in);

        while (input.hasNext()) {
            var line = input.nextLine();
            var arguments = line.split("\\s+");
            if (arguments.length == 0) {
                continue;
            }

            var commandName = arguments[0].toLowerCase();
            var params = Arrays.copyOfRange(arguments, 1, arguments.length);

            var command = commands.get(commandName);
            if (command != null) {
                command.execute(params);
            } else {
                System.out.println("Unknown command");
                commands.get("help").execute(new String[0]);
            }
        }
    }
}
