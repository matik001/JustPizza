package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.Command;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.translations.TranslationKey;

public class ExitCommand extends Command {
    public static final String name = "exit";

    public ExitCommand() {
        super(name);
    }

    @Override
    public String getDescription() {
        return AppSettings.global.translations.get(TranslationKey.exit_description);
    }
    @Override
    protected void initArgParser(ArgParser argParser) {
    }

    @Override
    protected boolean handleExecute(ShapesManager shapesManager, ArgParser argParser) {
        return false;
    }
}
