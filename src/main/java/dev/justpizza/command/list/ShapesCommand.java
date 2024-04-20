package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.Command;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.translations.TranslationKey;

public class ShapesCommand extends Command {
    public static final String name = "shapes";
    public static final String description = AppSettings.global.translations.get(TranslationKey.shapes_description);

    public ShapesCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
    }

    @Override
    protected boolean handleExecute(ShapesManager shapesManager, ArgParser argParser) {
        shapesManager.printShapes();
        return true;
    }

}
