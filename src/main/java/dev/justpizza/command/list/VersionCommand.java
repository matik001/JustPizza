package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.Command;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.translations.TranslationKey;

public class VersionCommand extends Command {
    public static final String name = "version";

    public VersionCommand() {
        super(name);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
    }

    @Override
    public String getDescription() {
        return AppSettings.global.translations.get(TranslationKey.version_description);
    }

    @Override
    protected boolean handleExecute(ShapesManager shapesManager, ArgParser argParser) {
        out.println(STR."\{AppSettings.global.programName} \{AppSettings.global.version}");
        return true;
    }
}
