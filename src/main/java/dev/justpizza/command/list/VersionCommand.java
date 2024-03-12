package dev.justpizza.command.list;

import dev.justpizza.command.Command;
import dev.justpizza.command.CommandManager;
import dev.justpizza.command.CommandParam;
import dev.justpizza.command.CommandParamSchema;
import dev.justpizza.config.AppSettings;

import java.util.ArrayList;
import java.util.List;

public class VersionCommand extends Command {

    public static final String name = "version";
    public static final List<CommandParamSchema> paramSchemaList = new ArrayList<>(){{
//        add(new CommandParamSchema("verbose", "v"))   example param schema
    }};
    public VersionCommand() {
        super(name, paramSchemaList);
    }

    @Override
    public void execute(List<CommandParam> params, CommandManager commandManager) {
        System.out.println(AppSettings.global.version);
    }
}
