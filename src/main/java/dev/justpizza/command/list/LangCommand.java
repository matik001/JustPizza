package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.command.Command;
import dev.justpizza.command.CommandManager;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.translations.TranslationKey;

import java.util.Arrays;
import java.util.HashSet;

public class LangCommand extends Command {
    public static final String name = "lang";

    public LangCommand() {
        super(name);
    }
    @Override
    public String getDescription() {
        return AppSettings.global.translations.get(TranslationKey.lang_description);
    }
    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema(new HashSet<>(Arrays.asList("eng", "pl"))));
        argParser.minNumberOfArgs = 1;
        argParser.maxNumberOfArgs = 1;
    }

    @Override
    protected boolean handleExecute(ShapesManager shapesManager, ArgParser argParser) {
        /// TODO we can change argparser to get value in better way
        AppSettings.global.loadTranslations(argParser.hasArg("pl") ? "pl" : "eng");
        out.println(AppSettings.global.translations.get(TranslationKey.changed_language));
        return true;
    }
}
