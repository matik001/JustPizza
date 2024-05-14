package dev.justpizza.command.list;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.argparser.ParamSchema;
import dev.justpizza.argparser.ParamType;
import dev.justpizza.command.Command;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.translations.TranslationKey;

import javax.sound.midi.SysexMessage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveToFileCommand extends Command {
    public static final String name = "save";

    public SaveToFileCommand() {
        super(name, AppSettings.global.translations.get(TranslationKey.save_description));
    }

    @Override
    protected void initArgParser(ArgParser argParser) {
        argParser.addParamSchema(new ParamSchema("filename", ParamType.STRING));
        argParser.minNumberOfArgs = 1;
        argParser.maxNumberOfArgs = 1;
    }

    @Override
    protected boolean handleExecute(ShapesManager shapesManager, ArgParser argParser) {
        var filename = argParser.getValueOrPanic("filename");
        File file = new File(filename.getString());
        (new Thread(() -> {
            try (FileOutputStream fop = new FileOutputStream(file)) {
                out.println("Saving...");

                if (!file.exists()) {
                    file.createNewFile();
                }
                shapesManager.jsonShapesToStream(fop);

                fop.flush();
                fop.close();
                out.println("Saved!");
            } catch (IOException e) {
                out.println("Couldn't save to file");
            }
        })).start();
        return true;
    }
}
