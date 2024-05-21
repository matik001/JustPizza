package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.Test;

import java.util.List;


public class IsoscelesTriangleCommandTest extends AbstractCommandTest {
    @Test
    public void createCorrectIsoscelesTriangle() {
        var commands = List.of(
                "isotriangle base 14.01 side 11.53",
                "isotriangle base 14.01 height 9.158",
                "isotriangle base 14.01 area 64.152",
                "isotriangle side 11.53 height 9.158",
                "isotriangle side 11.53 area 64.152",
                "isotriangle height 9.158 area 64.152"
        );
        var characteristics = List.of("IsoscelesTriangle [ ", " ]", "Base=14.01", "Side=11.53", "Height=9.16",
                "Perimeter=37.07", "Area=64.15");
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), characteristics);
    }


    @Test
    public void createIsoscelesTriangleWhichIsEquilateralTriangle() {
        var command = List.of("isotriangle base 10.91 side 10.91", "isotriangle base 7.89 height 6.83294");
        var characteristics = List.of("EquilateralTriangle [ ", " ]", "Side=10.91", "Perimeter=32.73", "Area=51.54");
        var characteristics2 = List.of("EquilateralTriangle [ ", " ]", "Side=7.89", "Perimeter=23.67", "Area=26.96");

        sendCommandsAndExpectLinesContaining(command, List.of(characteristics, characteristics2));
    }

    @Test
    public void disallowCreatingIncorrectIsoscelesTriangleFromBaseSide() {
        var commands = List.of(
                "isotriangle base 9.98 side 4.99",
                "isotriangle base 13.80 side 3.78"
        );
        var characteristics = List.of("IsoscelesTriangle with this parameters does not exist");
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), characteristics);
    }

    @Test
    public void disallowCreatingIncorrectIsoscelesTriangleFromHeightSide() {
        var commands = List.of(
                "isotriangle height 11.39 side 11.39",
                "isotriangle height 13.32 side 6.80"
        );
        var characteristics = List.of("IsoscelesTriangle with this parameters does not exist");
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), characteristics);
    }

    @Test
    public void disallowCreatingIncorrectIsoscelesTriangleFromSideArea() {
        var commands = List.of(
                "isotriangle side 5.96 area 18.36",
                "isotriangle side 5.96 area 17.77"
        );
        var characteristics = List.of("IsoscelesTriangle with this parameters does not exist");
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), characteristics);
    }

    @Test
    public void disallowCreatingIsoscelesTriangleWithBaseOrSideCloseToZero() {
        var commands = List.of(
                "isotriangle side 0.0000006 base 0.000001",
                "isotriangle side 7.03 base 0.00000001"
        );
        var characteristics = List.of("Side cannot be 0");
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), characteristics);
    }
}

