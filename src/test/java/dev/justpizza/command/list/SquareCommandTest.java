package dev.justpizza.command.list;

import dev.justpizza.command.AbstractCommandTest;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class SquareCommandTest extends AbstractCommandTest {
    @Test
    public void correctSquareTest() {
        var commands = List.of("square side 7", "square area 49", "square diagonal 9.8999");
        var characteristics = List.of("Square [ ", " ]", "Side=7.0", "Perimeter=28.0", "Area=49.0", "Diagonal=9.899");
        sendCommandsAndExpectEachLineContaining(commands, 3, characteristics);
    }

    @Test
    public void inconsistentCapitalizationTest() {
        var characteristics = List.of("Square [ ", " ]", "Side=6.25", "Perimeter=25.0", "Area=39.0625", "Diagonal=8.838834764831844");
        var inputCommands = List.of("square side 6.25", "SQUARE side 6.25", "square SIDE 6.25", "SQUARE SIDE 6.25",
                "SquArE siDe 6.25", "SqUArE siDe 6.25", "squARE SiDE 6.25", "SquArE sidE 6.25");
        sendCommandsAndExpectEachLineContaining(inputCommands, 8, characteristics);
    }

    @Test
    public void incorrectSquareParameterTest() {
        sendCommandsAndExpect("square sid 1", "Invalid argument at position 0: sid, expected one of: [side, diagonal, area]");
        sendCommandsAndExpect("square areaa 12", "Invalid argument at position 0: areaa, expected one of: [side, diagonal, area]");
        sendCommandsAndExpect("square d 15", "Invalid argument at position 0: d, expected one of: [side, diagonal, area]");
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
        sendCommandsAndExpect("square side 2 area 4", invalidUsageGiven2Parameters);
        sendCommandsAndExpect("square side 2  area 4 ", invalidUsageGiven2Parameters);

        // TODO gives "expected a positive number after area argument", but incorrect number of parameters has higher priority
//        sendCommandsAndExpect("square side 2 area", invalidUsageGiven2Parameters);
        // TODO gives "expected a positive number after area argument", but incorrect number of parameters has higher priority
//        sendCommandsAndExpect("square side 2 area ", invalidUsageGiven2Parameters);
        // TODO gives "Argument side already provided", but incorrect number of parameters has higher priority
//        sendCommandsAndExpect("square side 3 side 5", invalidUsageGiven2Parameters);
        // TODO gives "Invalid argument at position 2: 4, expected one of: [side, diagonal, area]", but incorrect number of parameters has higher priority
//        sendCommandsAndExpect("square side 4 4", invalidUsageGiven2Parameters);
    }
}