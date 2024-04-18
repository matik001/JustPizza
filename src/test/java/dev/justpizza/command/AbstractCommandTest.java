package dev.justpizza.command;

import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public abstract class AbstractCommandTest {
    private void sendCommand(PipedOutputStream pipedOutputStream, String input) throws IOException {
        pipedOutputStream.write(STR."\{input}\n".getBytes());
        pipedOutputStream.flush();
    }


    protected final void sendCommandsAndExpect(List<String> inputLines, Consumer<List<String>> outputVerifier) {
        try {
            var pipedInputStream = new PipedInputStream();
            var programInput = new PipedOutputStream(pipedInputStream);

            var programOutput = new PipedInputStream();
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
            Assertions.assertEquals(expectedLines.size(), outputLines.size(),
                    () -> STR."Expected \{expectedLines.size()} lines received \{outputLines.size()} lines");
            Assertions.assertIterableEquals(expectedLines, outputLines);
        });
    }

    protected final void sendCommandsAndExpect(String input, Consumer<List<String>> outputVerifier) {
        sendCommandsAndExpect(List.of(input), outputVerifier);
    }

    protected final void sendCommandAndExpect(String input, String expectedLine) {
        sendCommandAndExpect(input, List.of(expectedLine));
    }

    protected final void sendCommandAndExpect(String input, List<String> expectedLines) {
        sendCommandsAndExpect(List.of(input), expectedLines);
    }
}