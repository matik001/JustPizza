package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.argparser.ParamType;
import dev.justpizza.command.abstractList.CreateShapeCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.Shape;
import dev.justpizza.translations.TranslationKey;

public class DoubleCommand extends CreateShapeCommand {
    public static final String name = "double";
    public static final String description = AppSettings.global.translations.get(TranslationKey.double_description);

    public DoubleCommand(){ super(name, description); }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("shapenumber", ParamType.INT));
        argParser.minNumberOfArgs = 1;
        argParser.maxNumberOfArgs = 1;
    }

    @Override
    protected Shape createShape(ArgParser argParser) {
        return null;
    }
}
