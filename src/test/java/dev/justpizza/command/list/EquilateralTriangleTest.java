package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EquilateralTriangleTest extends AbstractCommandTest {
    @Test
    public void equilateralTest() {
        var commands = List.of("equtriangle side 6.2", "equtriangle area 16.645009", "equtriangle height 5.3693577");
        var characteristics = List.of("EquilateralTriangle [ ", " ]", "Side=6.2", "Perimeter=18.6", "Height=5.36935", "Area=16.64500");
        sendCommandsAndExpectEachLineContaining(commands, 3, characteristics);
    }
}
