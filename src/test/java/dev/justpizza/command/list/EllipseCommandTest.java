package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.Collection;
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
        var commands = List.of("ellipse semiminoraxis 1 semimajoraxis 1"
        );
        var characteristics = List.of("Circle [", "Radius=1.00", "Perimeter=6.28", "Area=3.14");
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), characteristics);
    }
}
