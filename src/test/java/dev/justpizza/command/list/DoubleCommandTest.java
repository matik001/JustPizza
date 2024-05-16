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

    @Test
    public void doubleRectangle() {
        var commands = List.of("rectangle sidea 5.43 sideb 4.13", "double shapenumber 1");
        var doubledCharacteristics = List.of("Rectangle [ ", "]", "Side A=7.68", "Side B=5.84", "Diagonal=9.65",
                "Perimeter=27.04", "Area=44.85");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), doubledCharacteristics));
    }

    @Test
    public void doubleRhombus() {
        var commands = List.of("rhombus diagonala 1.99 diagonalb 6.78", "double shapenumber 1");
        var doubledCharacteristics = List.of("Rhombus [ ", "]", "Side=5.00", "Diagonal A=9.59", "Diagonal B=2.81",
                "Perimeter=19.99", "Area=13.49");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), doubledCharacteristics));
    }

    @Test
    public void doubleIsoscelesTrapezium() {
        var commands = List.of("isotrapezium basea 12.01 baseb 2.41 height 1.52", "double shapenumber 1");
        var doubledCharacteristics = List.of("IsoscelesTrapezium [ ", "]", "Base A=3.41", "Base B=16.98",
                "Perimeter=34.63", "Area=21.92");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), doubledCharacteristics));
    }

    @Test
    public void doubleIsoscelesTriangle() {
        var commands = List.of("isotriangle base 9.52 height 3.1", "double shapenumber 1");
        var doubledCharacteristics = List.of("IsoscelesTriangle [ ", "]", "Base=13.46", "Side=8.03",
                "Perimeter=29.53", "Area=29.51");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), doubledCharacteristics));
    }

    @Test
    public void doubleRectangularTriangle() {
        var commands = List.of("rectriangle base 0.72 hypotenuse 2.41", "double shapenumber 1");
        var doubledCharacteristics = List.of("RectangularTriangle [ ", "]", "Base=1.02", "Altitude=3.25",
                "Perimeter=7.68", "Area=1.66");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), doubledCharacteristics));
    }

    @Test
    public void doubleTriangle() {
        var commands = List.of("triangle sides 6.43 11.11 7.13", "double shapenumber 1");
        var doubledCharacteristics = List.of("Triangle [ ", "]", "Side A=9.09", "Side B=10.08", "Side C=15.71",
                "Perimeter=34.89", "Area=43.10");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), doubledCharacteristics));
    }

    @Test
    public void doubleEllipseTest() {
        var commands = List.of("ellipse semiminoraxis 4.15 semimajoraxis 6.8", "double shapenumber 1");
        var characteristics = List.of("Ellipse [", "semiMinorAxis=4.15", "semiMajorAxis=6.80", "Perimeter=34.88", "Area=88.66");
        var doubledCharacteristics = List.of("Ellipse [", "semiMinorAxis=5.87", "semiMajorAxis=9.62", "Perimeter=49.33", "Area=177.31");
        sendCommandsAndExpectLinesContaining(commands, List.of(characteristics, doubledCharacteristics));
    }

    @Test
    public void doubleCircleTest() {
        var commands = List.of("circle radius 52.91", "double shapenumber 1");
        var doubledCharacteristics = List.of("Circle [", "Radius=74.83", "Perimeter=470.15", "Area=17589.58");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), doubledCharacteristics));
    }

}
