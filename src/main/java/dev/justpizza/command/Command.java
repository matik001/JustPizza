package dev.justpizza.command;

abstract public class Command {
    final protected String name;
    final protected String description;

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
