package dev.justpizza.translations;


import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;

public class Translations {
    final HashMap<TranslationKey, String> map = new HashMap<>();

    public static Translations load(String translationsPath) throws IOException {
        var translations = new Translations();
        var contentStream = Translations.class.getResourceAsStream(translationsPath);
        if (contentStream == null) {
            throw new FileNotFoundException(STR."Couldn't find resource \{translationsPath}");
        }
        var content = new String(contentStream.readAllBytes(), StandardCharsets.UTF_8);
        var json = new JSONObject(content);
        for (var key : json.keySet()) {
            var translation = json.getString(key);
            var enumKey = TranslationKey.valueOf(key);
            translations.map.put(enumKey, translation);
        }
        Locale.setDefault(Locale.US);
        return translations;
    }

    public String get(TranslationKey key) {
        var res = map.get(key);
        if (res == null)
            System.err.println(STR."Translation '\{key}' not found"); /// celowo nie uzywam tlumaczenia
        return res;
    }
}
