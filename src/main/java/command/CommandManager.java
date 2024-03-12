package command;

import command.list.HelpCommand;
import command.list.VersionCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    final public List<Command> commands = new ArrayList<>(){{
        add(new HelpCommand());
        add(new VersionCommand());
    }};
    public CommandManager(){

    }

    public Command getCommand(String[] args){
        var commandName = args.length > 0 ? args[0].toLowerCase() : "";
        var command = commands.stream().filter(a -> a.name.equals(commandName)).findAny();
        if(command.isEmpty())
            command = java.util.Optional.ofNullable(getCommand(new String[]{"help"}));
        return command.orElseThrow(); /// throw should not happen
    }

    public List<CommandParam> getCommandParams(String[] args){
        var command = getCommand(args);
        var res = new ArrayList<CommandParam>();
        for (int i = 1; i <args.length; i++) {
            var token = args[i];
            var shortName = token.startsWith("-") ? token.substring(1) : null;
            var longName = token.startsWith("--") ? token.substring(2) : null;
            var param = command.paramSchemaList.stream()
                            .filter(p-> p.paramFullName.equals(longName) || p.paramShortName.equals(shortName))
                            .findAny();
            if(param.isEmpty())
                throw new Error(STR."Param \{token} is illegal");
            var p = new CommandParam();
            p.schema = param.orElseThrow();
            if(p.schema.hasValue){
                p.paramValue = args[i+1]; /// throws if no value
                i++;
            }
            res.add(p);
        }
        return res;
    }

    public void execute(String[] args){
        var command = getCommand(args);
        var params = getCommandParams(args);
        command.execute(params, this);
    }

}
