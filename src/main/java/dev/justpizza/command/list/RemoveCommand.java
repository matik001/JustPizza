package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.argparser.ParamType;
import dev.justpizza.command.Command;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.translations.TranslationKey;

public class RemoveCommand extends Command {
    public static final String name = "remove";
    public static final String description = AppSettings.global.translations.get(TranslationKey.remove_description);

    public RemoveCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("number", ParamType.INT));
        argParser.minNumberOfArgs = 1;
        argParser.maxNumberOfArgs = 1;
    }

    @Override
    protected boolean handleExecute(ShapesManager shapesManager, ArgParser argParser) {
        var id = argParser.getValue("number").getInt();
        shapesManager.removeShape(id);
        return true;
    }

}
