package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Collection;
import java.util.List;

public class DoubleCommandTest extends AbstractCommandTest {
    @Test
    public void doubleSquare() {
        var commands = List.of("square side 9.1", "square side 18.8", "square side 17.3", "double shapenumber 2");
        List<List<String>> expectedSubstringsInLines = List.of(
                List.of(), List.of(), List.of(),
                List.of("Square [ ", "]", "Side=26.59", "Perimeter=106.35", "Area=706.88", "Diagonal=37.60")
        );
        sendCommandsAndExpectLinesContaining(commands, expectedSubstringsInLines);
    }

    @TestFactory
    public Collection<DynamicTest> doubleEquilateralTriangle() {
        var triangles = List.of("equtriangle side 12.3", "equtriangle area 65.510491", "equtriangle height 10.6521125");
        return triangles.stream().map((triangleCommand) ->
                DynamicTest.dynamicTest(triangleCommand, () -> {
                            var commands = List.of("equtriangle side 1.3", triangleCommand, "equtriangle side 13.9",
                                    "double shapenumber 2");
                            List<List<String>> expectedSubstringsInLines = List.of(
                                    List.of(), List.of(), List.of(),
                                    List.of("EquilateralTriangle [ ", "]", "Side=17.39", "Perimeter=52.18", "Area=131.02", "Height=15.06")
                            );
                            sendCommandsAndExpectLinesContaining(commands, expectedSubstringsInLines);
                        }
                )
        ).toList();

    }
}
