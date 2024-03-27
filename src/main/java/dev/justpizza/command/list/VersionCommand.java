package dev.justpizza.command.list;

import dev.justpizza.command.Command;
import dev.justpizza.config.AppSettings;

public class VersionCommand extends Command {
    public static final String name = "version";

    public VersionCommand() {
        super(name, "shows current version");
    }

    @Override
    public void execute(String[] params) {
        System.out.println(STR."\{AppSettings.global.programName} \{AppSettings.global.version}");
    }
}
