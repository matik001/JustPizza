package command.list;

import command.Command;
import command.CommandManager;
import command.CommandParam;
import command.CommandParamSchema;
import config.AppSettings;

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
