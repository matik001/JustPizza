package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DuplicateDetectionTest extends AbstractCommandTest {
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
    public void sameTriangle() {
        var commands = List.of("triangle sides 3 4 5", "rectriangle base 4 hypotenuse 5");
        var characteristics = List.of("RectangularTriangle [", "Perimeter=12.00", "Area=6.00", "Hypotenuse=5.00", "Base=3.00", "Altitude=4.00");
        sendCommandsAndExpectLinesContaining(commands,
                List.of(
                        characteristics,
                        List.of("Shape", "already exists")
                )
        );
    }
}
