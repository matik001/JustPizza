package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import dev.justpizza.shape.IllegalShapeException;
import dev.justpizza.shape.triangle.EquilateralTriangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DuplicateDetectionTest extends AbstractCommandTest {
    @Test
    public void createDifferentShapes() {
        var commands = new ArrayList<>(Arrays.asList(
                "circle radius 14.15",
                "ellipse semiminoraxis 1.66 semimajoraxis 4.54",
                "isotrapezium basea 16.55 baseb 19.96 height 12.54",
                "rectangle sidea 6.56 sideb 8.12",
                "rhombus diagonala 3.43 diagonalb 17.29",
                "square side 6.31",
                "equtriangle side 16.70",
                "isotriangle base 9.72 height 12.41",
                "rectriangle hypotenuse 15.50 area 19.83",
                "triangle sides 13.18 12.17 1.13",
                "reghexagon side 8.73"
        ));
        commands.add("circle radius 1"); // shape which will be compared (with .equals()) to every other shape
        var shapesCount = commands.size();
        commands.add("shapes");
        sendCommandsAndExpect(commands, (lines) -> {
            Assertions.assertEquals(shapesCount * 2, lines.size());
            for (int i = 0; i < shapesCount; i++) {
                var line = lines.get(shapesCount + i);
                var s = STR."\{i + 1}. ";
                assertTrue(line.contains(s), STR."\{s} was not found in output line:\n\{line}");
            }
        });
    }

    @Test
    public void sameCircle() {
        var commands = List.of(
                "circle radius 16.69",
                "circle area 875.1097973726",
                "circle perimeter 104.8663627768"
        );
        sendCommandsAndExpectLinesContaining(commands,
                List.of(
                        List.of(),
                        List.of("Shape Circle", "already exists"),
                        List.of("Shape Circle", "already exists")
                )
        );
    }

    @Test
    public void sameEllipsis() {
        var commands = List.of(
                "ellipse semiminoraxis 7.62 semimajoraxis 18.95",
                "ellipse semimajoraxis 18.95 semiminoraxis 7.62",
                "ellipse semiminoraxis 18.95 semimajoraxis 7.62",
                "ellipse semiminoraxis 7.62 area 453.6428375857"
        );
        sendCommandsAndExpectLinesContaining(commands,
                List.of(
                        List.of(),
                        List.of("Shape Ellipse", "already exists"),
                        List.of("Shape Ellipse", "already exists"),
                        List.of("Shape Ellipse", "already exists")
                )
        );
    }

    @Test
    public void sameSquare() {
        var commands = List.of("square side 5.17", "rectangle sidea 5.17 sideb 5.17");
        var characteristics = List.of("Square [ ", " ]", "Side=5.17", "Perimeter=20.68", "Area=26.73", "Diagonal=7.31");
        sendCommandsAndExpectLinesContaining(commands,
                List.of(
                        characteristics,
                        List.of("Shape", "already exists")
                )
        );
    }

    @Test
    public void sameEquilateralTriangle() throws IllegalShapeException {
        System.out.println(EquilateralTriangle.fromSide(11.73).getHeight());
        var commands = List.of(
                "equtriangle side 11.73",
                "equtriangle area 59.57947339018592",
                "equtriangle height 10.15847798639",
//                "isotriangle base 11.73 side 11.73", TODO fix isotriangle
                "triangle sides 11.73 11.73 11.73"
        );
        sendCommandsAndExpectLinesContaining(commands,
                List.of(
                        List.of(),
                        List.of("Shape EquilateralTriangle", "already exists"),
                        List.of("Shape EquilateralTriangle", "already exists"),
//                        List.of("Shape EquilateralTriangle", "already exists"), TODO fix isotriangle
                        List.of("Shape EquilateralTriangle", "already exists")
                )
        );
    }

    @Test
    public void sameRectangularTriangle() {
        var commands = List.of(
                "rectriangle base 14.96 altitude 17.85",
                "rectriangle altitude 14.96 base 17.85",
                "rectriangle base 14.96 hypotenuse 23.29",
                "triangle sides 14.96 17.85 23.29"

        );
        sendCommandsAndExpectLinesContaining(commands,
                List.of(
                        List.of(),
                        List.of("Shape RectangularTriangle", "already exists"),
                        List.of("Shape RectangularTriangle", "already exists"),
                        List.of("Shape RectangularTriangle", "already exists")
                )
        );
    }

    @Test
    public void sameTriangle() {
        var commands = List.of("triangle sides 3 4 5", "rectriangle base 4 hypotenuse 5");
        var characteristics = List.of("RectangularTriangle [", "Perimeter=12.00", "Area=6.00", "Hypotenuse=5.00", "Base=3.00",
                "Altitude=4.00");
        sendCommandsAndExpectLinesContaining(commands,
                List.of(
                        characteristics,
                        List.of("Shape", "already exists")
                )
        );
    }

    @Test
    public void sameIsoscelesTrapezium() {
        var commands = List.of(
                "isotrapezium basea 16.05 baseb 5.73 height 17.52",
                "isotrapezium basea 16.05 baseb 5.73 height 17.52",
                "isotrapezium basea 5.73 baseb 16.05 height 17.52",
                "isotrapezium basea 5.73 area 190.7928 baseb 16.05"
        );
        sendCommandsAndExpectLinesContaining(commands,
                List.of(
                        List.of(),
                        List.of("Shape IsoscelesTrapezium", "already exists"),
                        List.of("Shape IsoscelesTrapezium", "already exists"),
                        List.of("Shape IsoscelesTrapezium", "already exists")
                )
        );
    }

    @Test
    public void notSameIsoscelesTrapezium() {
        var commands = List.of(
                "isotrapezium basea 18.55 baseb 13.80 height 16.44",
                "isotrapezium basea 18.55 baseb 13.80 height 16.43",
                "isotrapezium basea 18.55 baseb 13.81 height 16.44",
                "shapes"
        );
        sendCommandsAndExpectLinesContaining(commands,
                List.of(
                        List.of(),
                        List.of(),
                        List.of(),
                        List.of("1. IsoscelesTrapezium [ "),
                        List.of("2. IsoscelesTrapezium [ "),
                        List.of("3. IsoscelesTrapezium [ ")
                )
        );
    }

    @Test
    public void sameRectangle() {
        var commands = List.of(
                "rectangle sidea 13.55 sideb 0.44",
                "rectangle sideb 0.44 sidea 13.55",
                "rectangle sidea 0.44 sideb 13.55",
                "rectangle sidea 13.55 area 5.962"
        );
        sendCommandsAndExpectLinesContaining(commands,
                List.of(
                        List.of(),
                        List.of("Shape Rectangle", "already exists"),
                        List.of("Shape Rectangle", "already exists"),
                        List.of("Shape Rectangle", "already exists")
                )
        );
    }

    @Test
    public void sameRhombus() {
        var commands = List.of(
                "rhombus diagonala 17.67 diagonalb 5.44",
                "rhombus diagonalb 5.44 diagonala 17.67",
                "rhombus diagonala 5.44 diagonalb 17.67",
                "rhombus diagonala 17.67 area 48.0624"
        );
        sendCommandsAndExpectLinesContaining(commands,
                List.of(
                        List.of(),
                        List.of("Shape Rhombus", "already exists"),
                        List.of("Shape Rhombus", "already exists"),
                        List.of("Shape Rhombus", "already exists")
                )
        );
    }

    @Test
    public void sameRegularHexagon() {
        var commands = List.of(
                "reghexagon side 6.58",
                "reghexagon area 112.48734687723771",
                "reghexagon perimeter 39.48"
        );
        sendCommandsAndExpectLinesContaining(commands,
                List.of(
                        List.of(),
                        List.of("Shape RegularHexagon", "already exists"),
                        List.of("Shape RegularHexagon", "already exists")
                )
        );
    }
}
