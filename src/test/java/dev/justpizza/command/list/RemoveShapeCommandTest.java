package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Collection;
import java.util.List;

public class RemoveShapeCommandTest extends AbstractCommandTest {
    @Test
    public void removeOnlyShape() {
        var commands = List.of("square side 5.17", "remove number 1");
        var characteristics = List.of("Square [ ", " ]", "Side=5.17", "Perimeter=20.68", "Area=26.73", "Diagonal=7.31");
        sendCommandsAndExpectLinesContaining(commands, List.of(
                List.of(),
                List.of("Removed:"),
                characteristics)
        );
    }

    @Test
    public void removeSecondOfThreeShapes() {
        var commands = List.of("equtriangle side 1.34",
                "rhombus diagonala 5.98 diagonalb 6.27",
                "square side 5.17",
                "remove number 2");
        var characteristics = List.of("Rhombus [ ", " ]", "Side=4.33", "Diagonal A=6.27", "Diagonal B=5.98",
                "Perimeter=17.33", "Area=18.75");
        sendCommandsAndExpectLinesContaining(commands, List.of(
                List.of(),
                List.of(),
                List.of(),
                List.of("Removed:"),
                characteristics)
        );
    }

    @TestFactory
    public Collection<DynamicTest> removeWithIntegerIdThatIsOutOfRange() {
        var ids = List.of(-15, -1, 0, 2, 49);
        return ids.stream().map(id -> DynamicTest.dynamicTest(STR."remove number \{id}", () -> {
            var commands = List.of("square side 91.82", STR."remove number \{id}");
            sendCommandsAndExpectLinesContaining(commands, List.of(
                    List.of(),
                    List.of("Shape with given id doesn't exists"))
            );
        })).toList();

    }
}