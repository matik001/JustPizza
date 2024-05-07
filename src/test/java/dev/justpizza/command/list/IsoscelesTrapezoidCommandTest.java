package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IsoscelesTrapezoidCommandTest extends AbstractCommandTest {
    @Test
    public void correctIsoscelesTrapezoidTest() {
        var commands = List.of("isotrapezoid basea 3 baseb 5 height 2",
                "isotrapezoid basea 5 baseb 3 height 2",
                "isotrapezoid basea 3 baseb 5 leg 2.236068");
        var characteristics = List.of("IsoscelesTrapezoid [ ", " ]", "Base A=3.00", "Base B=5.00", "Height=2.00", "Leg=2.24",
                "Area=8.00", "Perimeter=12.47");
        sendCommandsAndExpectEachLineContaining(commands, 3, characteristics);
    }

    @TestFactory
    public Collection<DynamicTest> createIsoscelesTrapezoidAndDoubleItsAreaTest() {
        String[] properties = {
                "basea 3.55",
                "baseb 4.17",
                "height 2.3",
                "leg 2.3207972767995",
                "area 8.878"
        };
        var isoscelesTrapezoids = new ArrayList<String>();
        for (int i = 0; i < properties.length; i++) {
            for (int j = i + 1; j < properties.length; j++) {
                for (int k = j + 1; k < properties.length; k++) {
                    if (i <= 1 && j == 3 && k == 4) continue;
                    isoscelesTrapezoids.add(STR."isotrapezoid \{properties[i]} \{properties[j]} \{properties[k]}");
                }
            }
        }

        return isoscelesTrapezoids.stream().map((command) ->
                DynamicTest.dynamicTest(command, () -> {
                            var commands = List.of(command, "double shapenumber 1");
                            List<String> createExpectedSubstrings = List.of("IsoscelesTrapezoid [ ", "]", "Base A=3.55", "Base B=4.17",
                                    "Height=2.30", "Leg=2.32", "Area=8.8", "Perimeter=12.36");
                            List<String> doubledExpectedSubstrings = List.of("IsoscelesTrapezoid [ ", "]", "Base A=5.02", "Base B=5.90",
                                    "Height=3.25", "Leg=3.28", "Area=17.76", "Perimeter=17.48");

                            sendCommandsAndExpectLinesContaining(commands, List.of(createExpectedSubstrings, doubledExpectedSubstrings));
                        }
                )
        ).toList();
    }
}
