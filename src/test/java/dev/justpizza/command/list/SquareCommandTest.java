package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class SquareCommandTest extends AbstractCommandTest {
    @Test
    public void correctSquareTest() {
        sendCommandAndExpect("square side 7", "Square [ Side=7.0, Perimeter=28.0, Area=49.0, Diagonal=9.899494936611665 ]");
        sendCommandAndExpect("square area 49", "Square [ Side=7.0, Perimeter=28.0, Area=49.0, Diagonal=9.899494936611665 ]");
        sendCommandAndExpect("square diagonal 9.899494936611665",
                "Square [ Side=6.999999999999999, Perimeter=27.999999999999996, Area=48.999999999999986, Diagonal=9.899494936611665 ]");
    }

    @Test
    public void inconsistentCapitalizationTest() {
        var expectedOutput = "Square [ Side=6.25, Perimeter=25.0, Area=39.0625, Diagonal=8.838834764831844 ]";
        var inputCommands = List.of("square side 6.25", "SQUARE side 6.25", "square SIDE 6.25", "SQUARE SIDE 6.25",
                "SquArE siDe 6.25", "SqUArE siDe 6.25", "squARE SiDE 6.25", "SquArE sidE 6.25");
        sendCommandsAndExpect(inputCommands, Collections.nCopies(inputCommands.size(), expectedOutput));
    }

    @Test
    public void incorrectSquareParameterTest() {
        sendCommandAndExpect("square sid 1", "Invalid argument at position 0: sid, expected one of: [side, diagonal, area]");
        sendCommandAndExpect("square areaa 12", "Invalid argument at position 0: areaa, expected one of: [side, diagonal, area]");
        sendCommandAndExpect("square d 15", "Invalid argument at position 0: d, expected one of: [side, diagonal, area]");
    }

    @Test
    public void tooManyParameters() {
        /*
        - Nieznane polecenie
        - Niepoprawna liczba parametrów
        - Nieznany parametr
        - Zła wartość parametru:
          - Nie jest to liczba a powinna być
          - Niepoprawna wartość
         */
        var invalidUsageGiven2Parameters = List.of(
                "Invalid usage of the command square:",
                "Required 1 parameters, given 2",
                "square [side] {positive_value} [diagonal] {positive_value} [area] {positive_value}"
        );
        sendCommandAndExpect("square side 2 area 4", invalidUsageGiven2Parameters);
        sendCommandAndExpect("square side 2  area 4 ", invalidUsageGiven2Parameters);
        sendCommandAndExpect("square side 2 area", "expected a positive number after area argument");
        sendCommandAndExpect("square side 2 area ", "expected a positive number after area argument");
        sendCommandAndExpect("square side 3 side 5", "Argument side already provided");
        sendCommandAndExpect("square side 4 4", "Invalid argument at position 2: 4, expected one of: [side, diagonal, area]");
    }
}