package dev.justpizza.command;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractCommandTest {
    static int DEFAULT_PIPE_SIZE = 65536;

    private void sendCommand(PipedOutputStream pipedOutputStream, String input) throws IOException {
        pipedOutputStream.write(STR."\{input}\n".getBytes());
        pipedOutputStream.flush();
    }

    protected final void sendCommandsAndExpect(List<String> inputLines, Consumer<List<String>> outputVerifier) {
        try {
            var pipedInputStream = new PipedInputStream(DEFAULT_PIPE_SIZE);
            var programInput = new PipedOutputStream(pipedInputStream);

            var programOutput = new PipedInputStream(DEFAULT_PIPE_SIZE);
            var pipedOutputStream = new PipedOutputStream(programOutput);
            var printStream = new PrintStream(pipedOutputStream);

            for (String line : inputLines) {
                sendCommand(programInput, line);
            }

            sendCommand(programInput, "exit");

            var commandManager = new CommandManager(pipedInputStream, printStream);
            commandManager.run();

            pipedInputStream.close();
            programInput.close();

            pipedOutputStream.close();
            var output = new String(programOutput.readAllBytes());
            System.out.print(output);
            programOutput.close();

            Scanner scanner = new Scanner(output);

            var outputLines = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                outputLines.add(scanner.nextLine());
            }
            outputVerifier.accept(outputLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected final void sendCommandsAndExpect(List<String> inputLines, List<String> expectedLines) {
        sendCommandsAndExpect(inputLines, (outputLines) -> {
            assertEquals(expectedLines.size(), outputLines.size(), () ->
                    STR."Expected \{expectedLines.size()} lines received \{outputLines.size()} lines\n" +
                    STR."Expected:\n\{String.join("\n", expectedLines)}\n" +
                    STR."Received:\n\{String.join("\n", outputLines)}\n"
            );
            assertIterableEquals(expectedLines, outputLines);
        });
    }

    protected final void sendCommandsAndExpect(String input, Consumer<List<String>> outputVerifier) {
        sendCommandsAndExpect(List.of(input), outputVerifier);
    }

    protected final void sendCommandsAndExpect(String input, String expectedLine) {
        sendCommandsAndExpect(input, List.of(expectedLine));
    }

    protected final void sendCommandsAndExpect(String input, List<String> expectedLines) {
        sendCommandsAndExpect(List.of(input), expectedLines);
    }

    protected final void sendCommandsAndExpectEachLineContaining(List<String> input, int expectedLines, List<String> expectedSubstrings) {
        sendCommandsAndExpect(input, (outputLines) -> {
            assertEquals(expectedLines, outputLines.size());
            for (String line : outputLines) {
                expectedSubstrings.forEach(s -> assertTrue(line.contains(s), STR."\{s} was not found in output line:\n\{line}"));
            }
        });
    }

    protected final void sendCommandsAndExpectEachLineContaining(String input, int expectedLines, List<String> expectedSubstrings) {
        sendCommandsAndExpectEachLineContaining(List.of(input), expectedLines, expectedSubstrings);
    }

    protected final void sendCommandsAndExpectLinesContaining(List<String> input, List<List<String>> expectedSubstringsInLines) {
        sendCommandsAndExpect(input, (outputLines) -> {
            assertEquals(expectedSubstringsInLines.size(), outputLines.size());
            for (int i = 0; i < outputLines.size(); i++) {
                var line = outputLines.get(i);
                var expectedSubstringsInLine = expectedSubstringsInLines.get(i);
                expectedSubstringsInLine.forEach(s -> assertTrue(line.contains(s), STR."\{s} was not found in output line:\n\{line}"));
            }
        });
    }

    protected final void sendCommandsAndExpectLinesContaining(String input, List<List<String>> expectedSubstrings) {
        sendCommandsAndExpectLinesContaining(List.of(input), expectedSubstrings);
    }
}