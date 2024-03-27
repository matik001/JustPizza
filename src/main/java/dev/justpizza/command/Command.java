package dev.justpizza.command;

abstract public class Command {
    final public String name;
    final public String description;

    protected Command(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void execute(String[] params);
}
