package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VersionCommandTest extends AbstractCommandTest {
    @Test
    public void versionTest() {
        sendCommandsAndExpect("version", (outputLines) -> {
            Assertions.assertEquals(1, outputLines.size());
            Assertions.assertTrue(outputLines.get(0).startsWith("JustPizza 1."));
        });
    }
}
