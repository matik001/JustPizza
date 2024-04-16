package dev.justpizza.command;

abstract public class Command {
    final protected String name;
    final protected String description;

    final protected int minNumberOfArgs;

    final protected int maxNumberOfArgs;

    protected Command(String name, String description, int minNumberOfArgs, int maxNumberOfArgs){
        this.name = name;
        this.description = description;
        this.minNumberOfArgs = minNumberOfArgs;
        this.maxNumberOfArgs = maxNumberOfArgs;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void execute(String[] params);
}
