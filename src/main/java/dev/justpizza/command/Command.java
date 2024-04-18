package dev.justpizza.command;

import dev.justpizza.argparser.ArgParser;

import java.io.PrintStream;

abstract public class Command {
    protected PrintStream out;
    final protected String name;
    final protected String description;

    protected abstract void initArgParser(ArgParser argParser);

    protected Command(String name, String description){
        this.name = name;
        this.description = description;
    }

    public void setOut(PrintStream out) {
        this.out = out;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    protected abstract boolean handleExecute(ArgParser argParser);

    /**
     * @return True if program should continue executing. False if program should exit.
     * */
    public boolean execute(String[] params){
        var argParser = new ArgParser();
        initArgParser(argParser);

        try {
            argParser.parseParams(params, name);
        } catch (IllegalArgumentException exc) {
            out.println(exc.getMessage());
            return true;
        }
        return handleExecute(argParser);
    }
}
