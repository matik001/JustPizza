package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.Command;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.translations.TranslationKey;

public class SortCommand extends Command {
    public static final String name = "sort";
    public static final String description = AppSettings.global.translations.get(TranslationKey.sort_description);

    public SortCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
    }

    @Override
    protected void handleExecute(ArgParser argParser) {
        ShapesManager.instance.sortShapes();
        ShapesManager.instance.printShapes();
    }
}
