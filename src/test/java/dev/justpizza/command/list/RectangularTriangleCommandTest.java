package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RectangularTriangleCommandTest extends AbstractCommandTest {
    @Test
    public void disallowCreatingImpossibleRectangularTriangleFromHypotenuseAndArea() {
        var command = "rectriangle hypotenuse 8.32 area 87.21";
        var message = "RectangularTriangle with this parameters does not exist";
        sendCommandsAndExpect(command, message);
    }

    @Test
    public void disallowCreatingImpossibleRectangularTriangleFromBaseAndHypotenuse() {
        var commands = List.of(
                "rectriangle base 2.41 hypotenuse 0.67",
                "rectriangle base 2.41 hypotenuse 2.41",
                "rectriangle altitude 2.41 hypotenuse 0.67",
                "rectriangle altitude 2.41 hypotenuse 2.41"
        );
        var message = "RectangularTriangle with this parameters does not exist";
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), List.of(message));
    }

    @Test
    public void createCorrectRectangularTriangle() {
        var commands = List.of(
                "rectriangle base 5.25 altitude 21.56",
                "rectriangle base 5.25 hypotenuse 22.19",
                "rectriangle base 5.25 area 56.60",
                "rectriangle altitude 21.56 hypotenuse 22.19",
                "rectriangle altitude 21.56 area 56.60",
                "rectriangle hypotenuse 22.19 area 56.60"
        );
        var characteristics = List.of("RectangularTriangle [", "Base=5.25", "Altitude=21.56", "Hypotenuse=22.19", "Perimeter=49.00",
                "Area=56.60");
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), characteristics);
    }

    @Test
    public void createRectangularTriangleWhichIsIsoscelesTriangle() {
        var commands = List.of(
                "rectriangle base 2.04 altitude 2.04",
                "rectriangle base 2.04 hypotenuse 2.88499566725",
                "rectriangle base 2.04 area 2.0808",
                "rectriangle altitude 2.04 hypotenuse 2.88499566725",
                "rectriangle altitude 2.04 area 2.0808",
                "rectriangle hypotenuse 2.88499566725 area 2.0808"
        );
        var characteristics = List.of("IsoscelesTriangle [", "Base=2.88", "Side=2.04", "Height=1.44", "Perimeter=6.96", "Area=2.08");
        sendCommandsAndExpectEachLineContaining(commands, commands.size(), characteristics);
    }
}
