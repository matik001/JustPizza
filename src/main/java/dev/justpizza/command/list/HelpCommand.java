package dev.justpizza.command.list;

import dev.justpizza.command.Command;
import dev.justpizza.command.CommandManager;

public class HelpCommand extends Command {
    public static final String name = "help";

    public HelpCommand() {
        super(name, "shows this help page");
    }

    @Override
    public void execute(String[] params) {
        System.out.println("Allowed usages:");
        for (var command : CommandManager.commands.values()) {
            System.out.println(STR."\{command.name} - \{command.description}");
        }
    }
}
