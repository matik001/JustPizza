package dev.justpizza.command;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.shape.IllegalShapeException;

abstract public class Command {
    final protected String name;
    final protected String description;

    protected abstract void initArgParser(ArgParser argParser);

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

    protected abstract void handleExecute(ArgParser argParser);

    public void execute(String[] params){
        var argParser = new ArgParser();
        initArgParser(argParser);

        try {
            argParser.parseParams(params, name);
        } catch (IllegalArgumentException exc) {
            System.out.println(exc.getMessage());
            return;
        }
        handleExecute(argParser);
    }
}
