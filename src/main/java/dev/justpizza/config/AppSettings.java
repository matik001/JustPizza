package dev.justpizza.config;

import dev.justpizza.translations.Translations;

import java.io.IOException;

public class AppSettings {
    public final String programName = "JustPizza";
    public final String version = "1.3";
    public Translations translations;


    public static AppSettings global = new AppSettings();
    public AppSettings(){
        var translationsPath = "src\\main\\java\\dev\\justpizza\\translations\\langs\\eng.json";

        try {
            // TODO wymyśleć coś mąrzejszego, by eng.json było w tym samym katalogu - może samo się kopiowało przy kompilacji
            translations = Translations.load(translationsPath);
        } catch (IOException e) {
            System.err.println("Could not load translations file: " + translationsPath);
            throw new RuntimeException(e);
        }
    }
}
