package dev.justpizza.command.list;

import dev.justpizza.command.Command;
import dev.justpizza.command.CommandManager;
import dev.justpizza.command.CommandParam;
import java.util.List;

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
