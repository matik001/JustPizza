package dev.justpizza.command;

import dev.justpizza.command.list.HelpCommand;
import dev.justpizza.command.list.VersionCommand;

import java.util.*;

public class CommandManager {
    final public static List<Command> commands = new ArrayList<>() {{
        add(new HelpCommand());
        add(new VersionCommand());
    }};

    public Command getCommand(String commandName) {
        var command = commands.stream()
                              .filter(a -> a.name.equals(commandName))
                              .findAny();

        if (command.isEmpty())
            command = Optional.of(getCommand("help"));
        return command.orElseThrow(); /// throw should not happen
    }

    public List<CommandParam> getCommandParams(String[] args) {
        // We don't know yet how parameters should be handled, so I left implementation empty
        return List.of();
    }

    public void run() {
        var input = new Scanner(System.in);

        while (input.hasNext()) {
            var line = input.nextLine();
            var arguments = line.split("\\s+");
            var commandName = arguments.length > 0 ? arguments[0].toLowerCase() : "";

            var command = getCommand(commandName);
            var params = getCommandParams(arguments);
            command.execute(commandName, params);
        }
    }
}
