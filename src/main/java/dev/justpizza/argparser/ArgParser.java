package dev.justpizza.argparser;

import java.util.*;

public class ArgParser {

    public final Map<String, Double> argValues = new HashMap<>();
    public List<List<String>> possibleArgs = new ArrayList<>();
    public ArgParser() {
    }



    public void parseParams(String[] params, String commandName) {
        var requiredParams = possibleArgs.size() * 2;

        if (params.length != requiredParams) {
            String result = STR."Invalid usage of the command \{commandName}: \n";
            result += STR."Required \{requiredParams} parameters, given \{params.length}\n";
            result += commandName;

            for (int i = 0; i < possibleArgs.size(); i++) {
                var keys = possibleArgs.get(i);
                result += STR." [\{String.join(" | ", keys)}] {positive value}";
            }
            throw new IllegalArgumentException(result);
        }
        for (int i = 0; i < possibleArgs.size(); i++) {
            String argName = params[i * 2].toLowerCase();

            if (!possibleArgs.get(i).contains(argName)) {
                throw new IllegalArgumentException("Invalid argument at position " + i
                        + ": " + argName + ", expected one of: " + possibleArgs.get(i));
            }
            if (argValues.containsKey(argName)) {
                throw new IllegalArgumentException("Argument " + argName + " already provided");
            }

            try {
                var argValue = Double.parseDouble(params[i * 2 + 1]);
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