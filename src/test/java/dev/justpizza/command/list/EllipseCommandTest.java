package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EllipseCommandTest extends AbstractCommandTest {
    @Test
    public void createCorrectEllipseTest() {
        var commands = List.of("ellipse area 6.28 semiminoraxis 1",
                "ellipse semiMajorAxis 2 semiMinorAxis 1",
                "ellipse semiMajorAxis 1 semiMinorAxis 2",
                "ellipse SEMIMAjorAXIS 2 semiMinorAxis 1"
        );
        var characteristics = List.of("semiMajorAxis=2.00", "Perimeter=9.68", "Area=6.28", "semiMinorAxis=1.00");
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), characteristics);
    }

    @Test
    public void createCircleTest() {
        var commands = List.of("ellipse semiminoraxis 1 semimajoraxis 1",
                "ellipse semiminoraxis 1 area 3.141592653589793"
        );
        var characteristics = List.of("Circle [", "Radius=1.00", "Perimeter=6.28", "Area=3.14");
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), characteristics);
    }

    @Test
    public void doubleEllipseTest() {
        var commands = List.of("ellipse semiminoraxis 4.15 semimajoraxis 6.8", "double shapenumber 1");
        var characteristics = List.of("Ellipse [", "semiMinorAxis=4.15", "semiMajorAxis=6.80", "Perimeter=34.88", "Area=88.66");
        var doubledCharacteristics = List.of("Ellipse [", "semiMinorAxis=5.87", "semiMajorAxis=9.62", "Perimeter=49.33", "Area=177.31");
        sendCommandsAndExpectLinesContaining(commands, List.of(characteristics, doubledCharacteristics));
    }

    @Test
    public void describeCircleOverEllipse() {
        var commands = List.of("ellipse semiminoraxis 5.69 semimajoraxis 10.01", "circumcircle shapenumber 1");
        var characteristics = List.of("Ellipse [", "semiMinorAxis=5.69", "semiMajorAxis=10.01", "Perimeter=50.23", "Area=178.94");
        var doubledCharacteristics = List.of("Circle [", "Radius=10.01", "Perimeter=62.89", "Area=314.79");
        sendCommandsAndExpectLinesContaining(commands, List.of(characteristics, doubledCharacteristics));
    }

    @Test
    public void disallowCreatingEclipseWithAxisEqualZero() {
        var commands = List.of(
                "ellipse semiMinorAxis 0 semiMajorAxis 1",
                "ellipse semiMinorAxis 1 semiMajorAxis 0"
        );
        var messageSemiMinorAxis = "expected a positive number after semiMinorAxis argument";
        var messageSemiMajorAxis = "expected a positive number after semiMajorAxis argument";
        sendCommandsAndExpectLinesContaining(commands,
                List.of(List.of(messageSemiMinorAxis), List.of(messageSemiMajorAxis))
        );
    }

    @Test
    public void disallowCreatingTooSmallEllipse() {
        var commands = List.of(
                "ellipse semiMinorAxis 1 semiMajorAxis 0.00000001",
                "ellipse semiMinorAxis 0.00000001 semiMajorAxis 1",
                "ellipse semiMinorAxis 0.00000001 semiMajorAxis 0.00000001",
                "ellipse semiMinorAxis 1 area 0.00000001",
                "ellipse semiMajorAxis 1 area 0.00000001"
        );
        var message = "Ellipse with this parameters does not exist";
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), List.of(message));
    }
}
