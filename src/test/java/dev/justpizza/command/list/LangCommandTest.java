package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LangCommandTest extends AbstractCommandTest {
    @Test
    public void changeLanguageToPolish() {
        var command = "lang pl";
        sendCommandsAndExpect(command, "Zmieniono język!");
    }

    @Test
    public void changeLanguageToPolishAndShowHelp() {
        var commands = List.of("lang pl", "help");
        sendCommandsAndExpect(commands, (lines) -> {
            var mergedLines = String.join("\n", lines);
            Assertions.assertTrue(mergedLines.contains("ellipse - oblicza cechy elipsy"));
            Assertions.assertTrue(mergedLines.contains("exit - zamyka program"));
            Assertions.assertTrue(mergedLines.contains("version - wyświetla aktualną wersję"));
        });
    }

    @Test
    public void changeLanguageToPolishAndBackToEnglish() {
        var commands = List.of("lang pl", "lang eng");
        sendCommandsAndExpect(commands, List.of("Zmieniono język!", "Language was changed!"));
    }

    @Test
    public void changeLanguageToPolishAndBackToEnglishAndShowHelp() {
        var commands = List.of("lang pl", "lang eng", "help");
        sendCommandsAndExpect(commands, (lines) -> {
            var mergedLines = String.join("\n", lines);
            Assertions.assertTrue(mergedLines.contains("ellipse - calculates ellipse characteristics"));
            Assertions.assertTrue(mergedLines.contains("exit - exits the program"));
            Assertions.assertTrue(mergedLines.contains("version - shows current version"));
        });
    }
}