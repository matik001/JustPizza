package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RegularHexagonCommandTest extends AbstractCommandTest {
    @Test
    public void createCorrectHexagon() {
        var commands = List.of(
                "reghexagon side 2",
                "reghexagon area 10.3923",
                "reghexagon perimeter 12"
        );
        var characteristics = List.of("RegularHexagon [ ", " ]", "Side=2.00", "Area=10.39", "Perimeter=12");
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), characteristics);
    }

    @Test
    public void createZeroHexagon() {
        var commands = List.of(
                "reghexagon side      0.000000001",
                "reghexagon area      0.0000000001",
                "reghexagon perimeter 0.0000000001"
        );
        var message = "Side cannot be 0";
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), List.of(message));
    }
}

