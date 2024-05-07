package dev.justpizza;

import dev.justpizza.command.CommandManager;

public class Main {
    public static void main(String[] args) {
        var commandManager = new CommandManager(System.in, System.out);
        commandManager.run();
    }
}