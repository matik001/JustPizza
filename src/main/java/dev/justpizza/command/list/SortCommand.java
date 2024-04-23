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
        argParser.addParamSchema(new ParamSchema(new HashSet<>(Arrays.asList("area", "perimeter", "date"))));
        argParser.addParamSchema(new ParamSchema(new HashSet<>(Arrays.asList("increasing", "decreasing"))));
        argParser.minNumberOfArgs = 2;
        argParser.maxNumberOfArgs = 2;
    }

    @Override
    protected void handleExecute(ArgParser argParser) {
        if(argParser.hasArg("date")){
            ShapesManager.instance.sortShapes("date", argParser.hasArg("increasing"));
        }else if(argParser.hasArg("area")){
            ShapesManager.instance.sortShapes("area", argParser.hasArg("increasing"));
        }else{
            ShapesManager.instance.sortShapes("perimeter", argParser.hasArg("increasing"));
        }
        //ShapesManager.instance.sortShapes(argParser.hasArg("area"), argParser.hasArg("increasing"));
        ShapesManager.instance.printShapes();
    }
}
