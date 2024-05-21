package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircumcircleCommandTest extends AbstractCommandTest {
    @Test
    public void createCircumcircleForRegularHexagon() {
        var commands = List.of("reghexagon side 14.39", "circumcircle shapenumber 1");
        var circleCharacteristics = List.of("Circle [ ", "]", "Radius=14.39", "Perimeter=90.42", "Area=650.54");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), circleCharacteristics));
    }

    @Test
    public void createCircumcircleForSquare() {
        var commands = List.of("square side 19.22", "circumcircle shapenumber 1");
        var circleCharacteristics = List.of("Circle [ ", "]", "Radius=13.59", "Perimeter=85.39", "Area=580.27");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), circleCharacteristics));
    }

    @Test
    public void createCircumcircleForRectangle() {
        var commands = List.of("rectangle sidea 4.22 sideb 5.40", "circumcircle shapenumber 1");
        var circleCharacteristics = List.of("Circle [ ", "]", "Radius=3.43", "Perimeter=21.53", "Area=36.89");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), circleCharacteristics));
    }

    @Test
    public void disallowCreatingCircumcircleForRhombus() {
        var commands = List.of("rhombus diagonala 8.84 diagonalb 3.26", "circumcircle shapenumber 1");
        var message = "Can't create circumcircle";
        sendCommandsAndExpect(commands, (outputLines) -> {
            assertEquals(2, outputLines.size());
            assertEquals(message, outputLines.get(1));
        });
    }

    @Test
    public void createCircumcircleForIsoscelesTrapezium() {
        var commands = List.of("isotrapezium basea 10.14 baseb 2.46 height 6.33", "circumcircle shapenumber 1");
        var circleCharacteristics = List.of("Circle [ ", "]", "Radius=5.22", "Perimeter=32.82", "Area=85.70");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), circleCharacteristics));
    }

    @Test
    public void createCircumcircleForEquilateralTriangle() {
        var commands = List.of("equtriangle side 22.33", "circumcircle shapenumber 1");
        var circleCharacteristics = List.of("Circle [ ", "]", "Radius=12.89", "Perimeter=81.00", "Area=522.16");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), circleCharacteristics));
    }

    @Test
    public void createCircumcircleForIsoscelesTriangle() {
        var commands = List.of("isotriangle side 10.08 base 5.71", "circumcircle shapenumber 1");
        var circleCharacteristics = List.of("Circle [ ", "]", "Radius=5.26", "Perimeter=33.02", "Area=86.76");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), circleCharacteristics));
    }

    @Test
    public void createCircumcircleForRectangularTriangle() {
        var commands = List.of("rectriangle hypotenuse 8.32 area 16.21", "circumcircle shapenumber 1");
        var circleCharacteristics = List.of("Circle [ ", "]", "Radius=4.16", "Perimeter=26.14", "Area=54.37");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), circleCharacteristics));
    }

    @Test
    public void createCircumcircleForTriangle() {
        var commands = List.of("triangle sides 17.44 12.98 5.07", "circumcircle shapenumber 1");
        var circleCharacteristics = List.of("Circle [ ", "]", "Radius=15.87", "Perimeter=99.71", "Area=791.23");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), circleCharacteristics));
    }

    @Test
    public void createCircumcircleForCircle() {
        var commands = List.of("circle radius 16.13", "circumcircle shapenumber 1");
        var circleCharacteristics = List.of("Circle [ ", "]", "Radius=16.13", "Perimeter=101.35", "Area=817.37");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), circleCharacteristics));
    }

    @Test
    public void createCircumcircleForEllipse() {
        var commands = List.of("ellipse semimajoraxis 4.09 semiminoraxis 1.96", "circumcircle shapenumber 1");
        var circleCharacteristics = List.of("Circle [ ", "]", "Radius=4.09", "Perimeter=25.70", "Area=52.55");
        sendCommandsAndExpectLinesContaining(commands, List.of(List.of(), circleCharacteristics));
    }
}
