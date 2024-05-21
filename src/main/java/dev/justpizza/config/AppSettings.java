package dev.justpizza.config;

import dev.justpizza.translations.Translations;

import java.io.IOException;

public class AppSettings {
    public final String programName = "JustPizza";
    public final String version = "1.7";
    public Translations translations;


    public static AppSettings global = new AppSettings();

    public void loadTranslations(String name){
        var translationsPath = STR."/langs/\{name}.json";

        try {
            translations = Translations.load(translationsPath);
        } catch (IOException e) {
            System.err.println(STR."Could not load translations resource file: \{translationsPath}");
            throw new RuntimeException(e);
        }
    }
    public AppSettings() {
        loadTranslations("eng");
    }

    public double getDoubleTolerance() {
        return 0.00001;
    }
}
