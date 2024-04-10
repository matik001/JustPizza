package dev.justpizza.command.list;

import dev.justpizza.command.Command;
import dev.justpizza.command.CommandManager;
import dev.justpizza.config.AppSettings;
import dev.justpizza.translations.TranslationKey;

public class HelpCommand extends Command {
    public static final String name = "help";

    public HelpCommand() {
        super(name, AppSettings.global.translations.get(TranslationKey.help_description));
    }

    @Override
    public void execute(String[] params) {
        System.out.println(AppSettings.global.translations.get(TranslationKey.allowed_usages)+ ":");
        for (var command : CommandManager.commands.values()) {
            System.out.println(STR."\{command.getName()} - \{command.getDescription()}");
        }
    }
}
