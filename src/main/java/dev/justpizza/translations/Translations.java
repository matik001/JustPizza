package dev.justpizza.translations;


import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Translations {
    final HashMap<TranslationKey, String> map = new HashMap<>();
    public static Translations load(String filename) throws IOException {
//        out.println("Working Directory = " + System.getProperty("user.dir"));
        var translations = new Translations();
        var path = Paths.get(filename);
        var content = Files.readString(path);
        var json = new JSONObject(content);
        for (var key : json.keySet()) {
            var translation = json.getString(key);
            var enumKey = TranslationKey.valueOf(key);
            translations.map.put(enumKey, translation);
        }
        return translations;
    }

    public String get(TranslationKey key) {
        var res = map.get(key);
        if(res == null)
            System.err.println(STR."Translation '\{key}' not found"); /// celowo nie uzywam tlumaczenia
        return res;
    }
}
