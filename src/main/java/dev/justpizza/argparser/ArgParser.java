package dev.justpizza.argparser;

import dev.justpizza.config.AppSettings;
import dev.justpizza.translations.TranslationKey;

import java.util.*;

public class ArgParser {

    public final Map<String, Param> argValues = new HashMap<>(); // hashmap lepszy od listy
    public List<List<ParamSchema>> paramsSchemaList = new ArrayList<>();

    public ArgParser() {
    }


    public void parseParams(String[] params, String commandName) {
        var requiredParams = paramsSchemaList.size() * 2;

        if (params.length != requiredParams) {
            String result = STR."\{AppSettings.global.translations.get(TranslationKey.invalid_usage_command)
                                                                  .replace("{commandName}", commandName)}: \n";
            result += STR."\{AppSettings.global.translations.get(TranslationKey.required_more_params)
                                                            .replace("{requiredParams}", String.valueOf(requiredParams))
                                                            .replace("{length}", String.valueOf(params.length))}\n";
            result += commandName;

            for (int i = 0; i < paramsSchemaList.size(); i++) {
                var keys = paramsSchemaList.get(i).stream().map(ParamSchema::getName).toList();
                result += STR." [\{String.join(" | ", keys)}] {\{AppSettings.global.translations.get(TranslationKey.positive_value)}}";
            }
            throw new IllegalArgumentException(result);
        }
        for (int i = 0; i < paramsSchemaList.size(); i++) {
            String argName = params[i * 2].toLowerCase();
            var schemas = paramsSchemaList.get(i);
            var schema = schemas.stream().filter(a -> a.getName().equals(argName)).findFirst();

            if (schema.isEmpty()) {
                throw new IllegalArgumentException(AppSettings.global.translations.get(TranslationKey.invalid_argument) + i
                                                   + ": " + argName + ", "
                                                   + AppSettings.global.translations.get(
                        TranslationKey.expected_one_of) + ": " + paramsSchemaList.get(i));
            }
            if (argValues.containsKey(argName)) {
                throw new IllegalArgumentException(
                        AppSettings.global.translations.get(TranslationKey.argument_already_provided).replace("{argName}", argName));
            }
            Object value;
            if (schema.get().getParamType() == ParamType.DOUBLE) {
                try {
                    var argValue = Double.parseDouble(params[i * 2 + 1]);
                    if (argValue <= 0) {
                        throw new IllegalArgumentException();
                    }
                    value = argValue;
                } catch (Exception e) {
                    throw new IllegalArgumentException(
                            AppSettings.global.translations.get(TranslationKey.expected_positive_number).replace("{argName}", argName));
                }
            } else if (schema.get().getParamType() == ParamType.INT) {
                try {
                    var argValue = Integer.parseInt(params[i * 2 + 1]);
                    if (argValue <= 0) {
                        throw new IllegalArgumentException();
                    }
                    value = argValue;
                } catch (Exception e) {
                    throw new IllegalArgumentException(
                            AppSettings.global.translations.get(TranslationKey.expected_positive_number).replace("{argName}", argName));
                }
            } else {
                value = params[i * 2 + 1];
            }
            argValues.put(argName, new Param(schema.get(), value));
        }
    }


    public Param getArgValue(String argName) {
        if (!argValues.containsKey(argName)) {
            throw new IllegalArgumentException(
                    AppSettings.global.translations.get(TranslationKey.argument_not_found).replace("{argName}", argName));
        }
        return argValues.get(argName);
    }
}