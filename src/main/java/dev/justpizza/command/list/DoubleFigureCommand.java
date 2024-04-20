package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.Shape;
import dev.justpizza.translations.TranslationKey;

public class DoubleFigureCommand extends CreateShapeCommand {
    public static final String name = "double";
    public static final String description =
            AppSettings.global.translations.get(TranslationKey.circumcircle_description);

    public DoubleFigureCommand(){ super(name, description); }

    @Override
    protected void initArgParser(ArgParser argParser) {
        return; //TODO: implement
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        return null;
    }
}
