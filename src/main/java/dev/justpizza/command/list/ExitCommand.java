package dev.justpizza.command.list;

import dev.justpizza.command.Command;

public class ExitCommand extends Command {
    public static final String name = "exit";

    public ExitCommand() {
        super(name, "exits the program");
    }

    @Override
    public void execute(String[] params) {
        System.exit(0);
    }
}
