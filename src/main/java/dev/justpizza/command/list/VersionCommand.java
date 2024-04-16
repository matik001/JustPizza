package dev.justpizza.command.list;

import dev.justpizza.command.Command;
import dev.justpizza.config.AppSettings;
import dev.justpizza.translations.TranslationKey;

public class VersionCommand extends Command {
    public static final String name = "version";

    public static final int minNumberOfArgs = 0;
    public static final int maxNumberOfArgs = 0;
    public VersionCommand() {
        super(name, AppSettings.global.translations.get(TranslationKey.version_description), minNumberOfArgs, maxNumberOfArgs);
    }

    @Override
    public void execute(String[] params) {
        System.out.println(STR."\{AppSettings.global.programName} \{AppSettings.global.version}");
    }
}
