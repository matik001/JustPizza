package dev.justpizza.command.list;

import dev.justpizza.command.Command;
import dev.justpizza.command.CommandManager;
import dev.justpizza.command.CommandParam;
import java.util.List;

public class HelpCommand extends Command {
    public static final String name = "help";

    public HelpCommand() {
        super(name, "shows this help page");
    }

    @Override
    public void execute(String commandName, List<CommandParam> params) {
        if (!commandName.equals(name)) {
            System.out.println("Unknown command");
        }
        System.out.println("Allowed usages:");
        for (var command : CommandManager.commands) {
            var line = new StringBuilder(STR."\{command.name} - \{command.description}");
            System.out.println(line);
        }
    }
}
