package dev.justpizza.command;

import java.util.List;

abstract public class Command {
    final public String name;
    final public String description;

    protected Command(String name, String description){
        this.name = name;
        this.description = description;
    }

    public abstract void execute(String commandName, List<CommandParam> params);
}
