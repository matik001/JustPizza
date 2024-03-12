package command;

import java.util.List;

abstract public class Command {
    final public List<CommandParamSchema> paramSchemaList;
    final public String name;
    protected Command(String name, List<CommandParamSchema> paramSchemaList){
        this.paramSchemaList = paramSchemaList;
        this.name = name;
    }

    public abstract void execute(List<CommandParam> params, CommandManager commandManager);
}
