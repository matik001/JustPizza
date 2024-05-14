package dev.justpizza.argparser;

import dev.justpizza.config.AppSettings;
import dev.justpizza.translations.TranslationKey;

import java.util.*;
import java.util.stream.Collectors;

public class ArgParser {

    public final Map<String, Param> argValues = new HashMap<>(); // hashmap lepszy od listy
    private List<ParamSchema> paramsSchemaList = new ArrayList<>();

    public int minNumberOfArgs;
    public int maxNumberOfArgs;

    public boolean hasArg(String argName) {
        return argValues.containsKey(argName);
    }

    public ArgParser() {
    }

    public Param getValue(String argName) {
        return argValues.get(argName.toLowerCase());
    }

    public Param getValueOrPanic(String argName) {
        if (!argValues.containsKey(argName)) {
            throw new IllegalArgumentException(
                    AppSettings.global.translations.get(TranslationKey.argument_not_found).replace("{argName}", argName));
        }
        return getValue(argName);
    }

    public void addParamSchema(ParamSchema paramSchema) {
        paramsSchemaList.add(paramSchema);
    }

    public void parseParams(String[] params, String commandName) {

        for (int i = 0; i < params.length; ) {
            String argName = params[i];
            //var schemas = paramsSchemaList.get(i);
            var schema = paramsSchemaList.stream().filter(a -> a.getParamType() != ParamType.OPTIONS_SET &&
                    a.getName().equalsIgnoreCase(argName)).findFirst();
            if (schema.isEmpty()) {
                schema = paramsSchemaList
                        .stream()
                        .filter(a -> a.hasOption(argName))
                        .findFirst();
                if (schema.isPresent()) {
                    argValues.put(argName.toLowerCase(), new Param(schema.get(), argName));
                    i++;
                    continue;
                }
            }
            if (schema.isEmpty()) {
                var invalidArgument = AppSettings.global.translations.get(TranslationKey.invalid_argument);
                var expectedOneOf = AppSettings.global.translations.get(TranslationKey.expected_one_of);
                var allowedParameters = paramsSchemaList.stream().map(a -> a.getParamType() == ParamType.OPTIONS_SET
                                ? String.join(", ", a.getOptionsSet())
                                : a.getName())
                        .collect(Collectors.joining(", "));
                var message = STR."\{invalidArgument} \{i}: \{argName}, \{expectedOneOf}: [\{allowedParameters}]";
                throw new IllegalArgumentException(message);
            }
            if (schema.get().getParamType() != ParamType.OPTIONS_SET && argValues.containsKey(argName.toLowerCase())) {
                throw new IllegalArgumentException(
                        AppSettings.global.translations.get(TranslationKey.argument_already_provided).replace("{argName}", argName));
            }
            i++;
            Object paramValue = null;
            for (int k = 0; k < schema.get().getArrLen() || !schema.get().isArray(); k++) {
                Object value;
                if (schema.get().getParamType() == ParamType.POSITIVE_DOUBLE) {
                    try {
                        var argValue = Double.parseDouble(params[i]);
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
                        value = Integer.parseInt(params[i]);
                    } catch (Exception e) {
                        throw new IllegalArgumentException(
                                AppSettings.global.translations.get(TranslationKey.expected_positive_number).replace("{argName}", argName));
                    }
                } else {
                    value = params[i];
                }
                i++;
                if (k == 0) {
                    if (!schema.get().isArray()) {
                        paramValue = value;
                        break;
                    } else {
                        paramValue = new ArrayList<Object>();
                    }
                }
                ((ArrayList<Object>) paramValue).add(value);
            }

            argValues.put(argName.toLowerCase(), new Param(schema.get(), paramValue));
        }
        if (argValues.size() < minNumberOfArgs || argValues.size() > maxNumberOfArgs) {
            var commandUsage = AppSettings.global.translations.get(TranslationKey.invalid_usage_command);
            StringBuilder result = new StringBuilder(STR."\{commandUsage.replace("{commandName}", commandName)}:\n");
            var requiredMoreParams = AppSettings.global.translations.get(TranslationKey.required_more_params);
            var required = minNumberOfArgs == maxNumberOfArgs ? String.valueOf(maxNumberOfArgs) : String.valueOf(minNumberOfArgs) + " - " + String.valueOf(maxNumberOfArgs);
            result.append(STR."\{requiredMoreParams.replace("{requiredParams}", required)
                    .replace("{length}", String.valueOf(argValues.size()))}\n");
            result.append(commandName);
            List<String> keys = paramsSchemaList.stream()
                    .map(a -> a.getParamType() == ParamType.OPTIONS_SET
                            ? "[" + String.join(" | ", a.getOptionsSet()) + "]"
                            : "[" + a.getName() + "] {" + a.getValueDesc() + "}")
                    .toList();
            //var keys = paramSchemas.stream().map(ParamSchema::getName).toList();
            var positiveValue = AppSettings.global.translations.get(TranslationKey.positive_value);
            result.append(STR." \{String.join(" ", keys)}");
            throw new IllegalArgumentException(result.toString());
        }
    }


}