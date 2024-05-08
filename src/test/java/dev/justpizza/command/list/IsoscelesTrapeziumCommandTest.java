package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IsoscelesTrapeziumCommandTest extends AbstractCommandTest {
    @Test
    public void createCorrectIsoscelesTrapeziumTest() {
        var commands = List.of("isotrapezium basea 3 baseb 5 height 2",
                "isotrapezium basea 3 baseb 5 leg 2.236068",
                "isotrapezium basea 3 baseb 5 area 8",
                "isotrapezium height 2 area 8 leg 2.236068",
                "isotrapezium baseb 5 height 2 leg 2.236068"
        );
        var characteristics = List.of("IsoscelesTrapezium [ ", " ]", "Base A=3.00", "Base B=5.00", "Height=2.00", "Leg=2.24",
                "Area=8.00", "Perimeter=12.47");
        sendCommandsAndExpectEachLineContaining(commands, 5, characteristics);
    }

    @TestFactory
    public Collection<DynamicTest> createIsoscelesTrapeziumAndDoubleItsAreaTest() {
        String[] properties = {
                "basea 3.55",
                "baseb 4.17",
                "height 2.3",
                "leg 2.3207972767995",
                "area 8.878"
        };
        var isoscelesTrapeziums = new ArrayList<String>();
        for (int i = 0; i < properties.length; i++) {
            for (int j = i + 1; j < properties.length; j++) {
                for (int k = j + 1; k < properties.length; k++) {
                    if (i <= 1 && j == 3 && k == 4) continue;
                    isoscelesTrapeziums.add(STR."isotrapezium \{properties[i]} \{properties[j]} \{properties[k]}");
                }
            }
        }

        return isoscelesTrapeziums.stream().map((command) ->
                DynamicTest.dynamicTest(command, () -> {
                            var commands = List.of(command, "double shapenumber 1");
                            List<String> createExpectedSubstrings = List.of("IsoscelesTrapezium [ ", "]", "Base A=3.55", "Base B=4.17",
                                    "Height=2.30", "Leg=2.32", "Area=8.8", "Perimeter=12.36");
                            List<String> doubledExpectedSubstrings = List.of("IsoscelesTrapezium [ ", "]", "Base A=5.02", "Base B=5.90",
                                    "Height=3.25", "Leg=3.28", "Area=17.76", "Perimeter=17.48");

                            sendCommandsAndExpectLinesContaining(commands, List.of(createExpectedSubstrings, doubledExpectedSubstrings));
                        }
                )
        ).toList();
    }

    @Test
    public void illegalIsoscelesTrapeziumFromBasesAndLeg() {
        var commands = List.of(
                "isotrapezium basea 4 baseb 12 leg 1",
                "isotrapezium basea 12 baseb 4 leg 1"
        );
        var message = "IsoscelesTrapezium with this parameters does not exist";
        sendCommandsAndExpect(commands, List.of(message, message));
    }

    @Test
    public void illegalIsoscelesTrapeziumFromBaseAndHeightAndLeg() {
        var commands = List.of(
                "isotrapezium basea 4 height 5 leg 4.9",
                "isotrapezium baseb 4 height 5 leg 4.9"
        );
        var message = "IsoscelesTrapezium with this parameters does not exist";
        sendCommandsAndExpect(commands, List.of(message, message));
    }

    @Test
    public void illegalIsoscelesTrapeziumFromHeightAndLegAndArea() {
        var commands = List.of(
                "isotrapezium area 10 height 5 leg 4.9",
                "isotrapezium area 1 height 4 leg 5.5"
        );
        var message = "IsoscelesTrapezium with this parameters does not exist";
        sendCommandsAndExpect(commands, List.of(message, message));
    }

    @Test
    public void illegalIsoscelesTrapeziumFromBaseAndHeightAndArea() {
        var commands = List.of(
                "isotrapezium basea 3.1 height 8 area 12.4",
                "isotrapezium baseb 3.1 height 8 area 12.4",
                "isotrapezium basea 7.33 height 9 area 15.1",
                "isotrapezium baseb 7.33 height 9 area 15.1"
        );
        var message = "IsoscelesTrapezium with this parameters does not exist";
        sendCommandsAndExpect(commands, List.of(message, message, message, message));
    }

    @Test
    public void createIsoTrapeziumAndDescribeACircle() {
        var commands = List.of("isotrapezium basea 4 baseb 5 leg 13", "circumcircle shapenumber 1");
        List<List<String>> expectedSubstrings = List.of(List.of(), List.of("Circle", "Radius=6.88", "Perimeter=43.22", "Area=148.66"));
        sendCommandsAndExpectLinesContaining(commands, expectedSubstrings);
    }

    @Test
    public void createIsoscelesTrapeziumWhichIsRectangle() {
        var command = "isotrapezium basea 5 baseb 5 height 12";
        List<String> expectedSubstrings = List.of("Rectangle [ ", "]", "Side A=5.00", "Side B=12.00",
                "Perimeter=34.00", "Area=60.00");
        sendCommandsAndExpectLinesContaining(command, List.of(expectedSubstrings));
    }
}
