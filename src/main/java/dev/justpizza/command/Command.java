package dev.justpizza.command;

import dev.justpizza.argparser.ArgParser;
import dev.justpizza.shape.ShapesManager;

import java.io.PrintStream;

abstract public class Command {
    protected PrintStream out;
    final protected String name;

    protected abstract void initArgParser(ArgParser argParser);

    protected Command(String name){
        this.name = name;
    }

    public void setOut(PrintStream out) {
        this.out = out;
    }

    public String getName() {
        return name;
    }

    public abstract String getDescription();

    protected abstract boolean handleExecute(ShapesManager shapesManager, ArgParser argParser);

    /**
     * @return True if program should continue executing. False if program should exit.
     * */
    public boolean execute(String[] params, ShapesManager shapesManager){
        var argParser = new ArgParser();
        initArgParser(argParser);

        try {
            argParser.parseParams(params, name);
        } catch (IllegalArgumentException exc) {
            out.println(exc.getMessage());
            return true;
        }
        return handleExecute(shapesManager, argParser);
    }
}
