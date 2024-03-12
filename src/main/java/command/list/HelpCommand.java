package command.list;

import command.Command;
import command.CommandManager;
import command.CommandParam;
import command.CommandParamSchema;
import config.AppSettings;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand extends Command {
    public static final String name = "help";
    public static final List<CommandParamSchema> paramSchemaList = new ArrayList<>(){{
//        add(new CommandParamSchema("verbose", "v"))   example param schema
    }};
    public HelpCommand() {
        super(name, paramSchemaList);
    }
    @Override
    public void execute(List<CommandParam> params, CommandManager commandManager) {
        System.out.println("Allowed usages:");
        for(var command : commandManager.commands){
            StringBuilder line = new StringBuilder(STR."./\{AppSettings.global.programName} \{command.name}");
            for(var param : command.paramSchemaList){
                line.append(STR." --\{param.paramFullName}(-\{param.paramShortName})");
                if(param.hasValue){
                    line.append(" [value]");
                }
            }
            System.out.println(line.toString());
        }
    }
}
