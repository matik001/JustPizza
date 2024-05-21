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
    public SortCommand() {
        super(name);
    }

    @Override
    public String getDescription() {
        return AppSettings.global.translations.get(TranslationKey.sort_description);
    }
    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema(new HashSet<>(Arrays.asList("area", "perimeter", "date"))));
        argParser.addParamSchema(new ParamSchema(new HashSet<>(Arrays.asList("increasing", "decreasing"))));
        argParser.minNumberOfArgs = 2;
        argParser.maxNumberOfArgs = 2;
    }

    @Override
    protected boolean handleExecute(ShapesManager shapesManager, ArgParser argParser) {
        if (argParser.hasArg("date")) {
            shapesManager.sortShapes("date", argParser.hasArg("increasing"));
        } else if (argParser.hasArg("area")) {
            shapesManager.sortShapes("area", argParser.hasArg("increasing"));
        } else {
            shapesManager.sortShapes("perimeter", argParser.hasArg("increasing"));
        }
        shapesManager.printShapes();
        return true;
    }
}
