package dev.justpizza.utils;

import java.util.HashMap;
import java.util.Map;

import dev.justpizza.config.AppSettings;
import dev.justpizza.translations.TranslationKey;

public class Utils {
    public static Map<String, Object> mergeProperties(Map<String, Object> first, Map<String, Object> second) {
        Map<String, Object> result = new HashMap<>(first);
        result.putAll(second);
        return result;
    }
}
