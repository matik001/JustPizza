package dev.justpizza;

import command.Command;
import command.CommandManager;
import command.list.HelpCommand;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var commandManager = new CommandManager();
        commandManager.execute(args);
    }
}