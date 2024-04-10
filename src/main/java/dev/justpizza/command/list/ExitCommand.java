package dev.justpizza.command.list;

import dev.justpizza.command.Command;
import dev.justpizza.config.AppSettings;
import dev.justpizza.translations.TranslationKey;

public class ExitCommand extends Command {
    public static final String name = "exit";

    public ExitCommand() {
        super(name, AppSettings.global.translations.get(TranslationKey.exit_description));
    }

    @Override
    public void execute(String[] params) {
        System.exit(0);
    }
}
