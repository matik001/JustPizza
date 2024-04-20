package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.Command;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.translations.TranslationKey;

import java.util.Arrays;
import java.util.HashSet;

public class SortCommand extends Command {
    public static final String name = "sort";
    public static final String description = AppSettings.global.translations.get(TranslationKey.sort_description);

    public SortCommand() {
        super(name, description);
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema(new HashSet<>(Arrays.asList("area", "perimeter"))));
        argParser.addParamSchema(new ParamSchema(new HashSet<>(Arrays.asList("increasing", "decreasing"))));
        argParser.minNumberOfArgs = 2;
        argParser.maxNumberOfArgs = 2;
    }

    @Override
    protected boolean handleExecute(ShapesManager shapesManager, ArgParser argParser) {
        shapesManager.sortShapes(argParser.hasArg("area"), argParser.hasArg("increasing"));
        shapesManager.printShapes();
        return true;
    }
}
