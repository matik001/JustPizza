package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TriangleCommandTest extends AbstractCommandTest {
    @Test
    public void createCorrectTriangle() {
        var commands = "triangle sides 13.26 31.13 22.45";
        var characteristics = List.of("Triangle [ ", " ]", "Side A=13.26", "Side B=22.45", "Side C=31.13",
                "Perimeter=66.84", "Area=130.10");
        sendCommandsAndExpectLinesContaining(commands, List.of(characteristics));
    }

    @Test
    public void disallowCreatingImpossibleTriangle() {
        var commands = List.of(
                "triangle sides 3.43 4.13 11.11",
                "triangle sides 3.43 11.11 4.13",
                "triangle sides 4.13 3.43 11.11",
                "triangle sides 4.13 11.11 3.43",
                "triangle sides 11.11 3.43 4.13",
                "triangle sides 11.11 4.13 3.43"
        );
        var message = "Triangle with this parameters does not exist";
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), List.of(message));
    }

    @Test
    public void createTriangleWhichIsEquilateralTriangle() {
        var commands = "triangle sides 7.81 7.81 7.81";
        var characteristics = List.of("EquilateralTriangle [ ", " ]", "Side=7.81", "Height=6.76",
                "Perimeter=23.43", "Area=26.41");
        sendCommandsAndExpectLinesContaining(commands, List.of(characteristics));
    }

    @Test
    public void createTriangleWhichIsIsoscelesTriangleWithLongerBase() {
        var commands = List.of(
                "triangle sides 7.98 6.12 6.12",
                "triangle sides 6.12 7.98 6.12",
                "triangle sides 6.12 6.12 7.98"
        );
        var characteristics = List.of("IsoscelesTriangle [ ", " ]", "Side=6.12", "Base=7.98", "Height=4.64",
                "Perimeter=20.22", "Area=18.52");
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), characteristics);
    }

    @Test
    public void createTriangleWhichIsIsoscelesTriangleWithShorterBase() {
        var commands = List.of(
                "triangle sides 1.33 2.98 2.98",
                "triangle sides 2.98 1.33 2.98",
                "triangle sides 2.98 2.98 1.33"
        );
        var characteristics = List.of("IsoscelesTriangle [ ", " ]", "Side=2.98", "Base=1.33", "Height=2.90",
                "Perimeter=7.29", "Area=1.93");
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), characteristics);
    }

    @Test
    public void createTriangleWhichIsRectangularTriangle() {
        var commands = List.of(
                "triangle sides 3.5 5.393015 4.103",
                "triangle sides 3.5 4.103 5.393015",
                "triangle sides 4.103 3.5 5.393015",
                "triangle sides 4.103 5.393015 3.5",
                "triangle sides 5.393015 3.5 4.103",
                "triangle sides 5.393015 4.103 3.5"
        );
        var characteristics = List.of("RectangularTriangle [ ", " ]", "Base=3.50", "Altitude=4.10", "Hypotenuse=5.39",
                "Perimeter=13.00", "Area=7.18");
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), characteristics);
    }
}
