package dev.justpizza.argparser;

import java.util.*;

public class ArgParser {

    public final Map<String, Double> argValues = new HashMap<>();

    public ArgParser() {
    }

    public void parseParams(List<List<String>> possibleArgs, String[] params, String commandName) {
        if (params.length != possibleArgs.size() * 2) {

            String result = "Invalid usage of the command " + commandName + ": \n" + commandName;

            for (int i = 0; i < possibleArgs.size(); i++) {
                var keys = possibleArgs.get(i);
                result += " [" + String.join("|", keys) + "] {positive value}";
            }
            throw new IllegalArgumentException(result);
        }
        for (int i = 0; i < params.length; i += 2) {
            String argName = params[i].toLowerCase();

            if (!possibleArgs.get(i).contains(argName)) {
                throw new IllegalArgumentException("Invalid argument at position " + i
                        + ": " + argName + ", expected one of: " + possibleArgs.get(i));
            }
            if (argValues.containsKey(argName)) {
                throw new IllegalArgumentException("Argument " + argName + " already provided");
            }

            try {
                var argValue = Double.parseDouble(params[i + 1]);
                System.out.println(argValue);
                if (argValue <= 0) {
                    throw new IllegalArgumentException();
                }
                argValues.put(argName, argValue);
            } catch (Exception e) {
                throw new IllegalArgumentException("expected a positive number after " + argName + " argument");
            }
        }
    }


    public double getArgValue(String argName) {
        if (!argValues.containsKey(argName)) {
            throw new IllegalArgumentException("Argument not found: " + argName);
        }
        return argValues.get(argName);
    }
}