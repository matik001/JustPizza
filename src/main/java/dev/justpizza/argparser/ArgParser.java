package dev.justpizza.argparser;

import dev.justpizza.config.AppSettings;
import dev.justpizza.translations.TranslationKey;

import java.util.*;
import java.util.stream.Collectors;

public class ArgParser {

    public final Map<String, Param> argValues = new HashMap<>(); // hashmap lepszy od listy
    public List<List<ParamSchema>> paramsSchemaList = new ArrayList<>();

    public ArgParser() {
    }


    public void parseParams(String[] params, String commandName) {
        var requiredParams = paramsSchemaList.size() * 2;

        if (params.length != requiredParams) {
            var commandUsage = AppSettings.global.translations.get(TranslationKey.invalid_usage_command);
            String result = STR."\{commandUsage.replace("{commandName}", commandName)}: \n";
            var requiredMoreParams = AppSettings.global.translations.get(TranslationKey.required_more_params);
            result += STR."\{requiredMoreParams.replace("{requiredParams}", String.valueOf(requiredParams))
                                               .replace("{length}", String.valueOf(params.length))}\n";
            result += commandName;

            for (int i = 0; i < paramsSchemaList.size(); i++) {
                var keys = paramsSchemaList.get(i).stream().map(ParamSchema::getName).toList();
                var positiveValue = AppSettings.global.translations.get(TranslationKey.positive_value);
                result += STR." [\{String.join(" | ", keys)}] {\{positiveValue}}";
            }
            throw new IllegalArgumentException(result);
        }
        for (int i = 0; i < paramsSchemaList.size(); i++) {
            String argName = params[i * 2].toLowerCase();
            var schemas = paramsSchemaList.get(i);
            var schema = schemas.stream().filter(a -> a.getName().equals(argName)).findFirst();

            if (schema.isEmpty()) {
                var invalidArgument = AppSettings.global.translations.get(TranslationKey.invalid_argument);
                var expectedOneOf = AppSettings.global.translations.get(TranslationKey.expected_one_of);
                var allowedParameters = paramsSchemaList.get(i).stream().map(ParamSchema::getName).collect(Collectors.joining(", "));
                var message = STR."\{invalidArgument} \{i}: \{argName}, \{expectedOneOf}: [\{allowedParameters}]";
                throw new IllegalArgumentException(message);
            }
            if (argValues.containsKey(argName)) {
                throw new IllegalArgumentException(
                        AppSettings.global.translations.get(TranslationKey.argument_already_provided).replace("{argName}", argName));
            }
            Object value;
            if (schema.get().getParamType() == ParamType.POSITIVE_DOUBLE) {
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
                    value = Integer.parseInt(params[i * 2 + 1]);
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