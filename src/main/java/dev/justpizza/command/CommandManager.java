package dev.justpizza.command;

import dev.justpizza.command.list.*;
import dev.justpizza.command.list.ellipse.CircleCommand;
import dev.justpizza.command.list.ellipse.CircumcircleCommand;
import dev.justpizza.command.list.quadrangle.IsoscelesTrapeziumCommand;
import dev.justpizza.command.list.quadrangle.RectangleCommand;
import dev.justpizza.command.list.quadrangle.RhombusCommand;
import dev.justpizza.command.list.quadrangle.SquareCommand;
import dev.justpizza.command.list.triangle.EquilateralTriangleCommand;
import dev.justpizza.command.list.triangle.IsoscelesTriangleCommand;
import dev.justpizza.command.list.triangle.RectangularTriangleCommand;
import dev.justpizza.command.list.triangle.TriangleCommand;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.ShapesManager;
import dev.justpizza.translations.TranslationKey;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommandManager {
    private final InputStream inputStream;
    private final PrintStream outputStream;

    final private static List<Command> commandsList = new ArrayList<>() {{
        add(new HelpCommand());
        add(new ShapesCommand());
        add(new VersionCommand());
        add(new SquareCommand());
        add(new TriangleCommand());
        add(new EquilateralTriangleCommand());
        add(new IsoscelesTriangleCommand());
        add(new IsoscelesTrapeziumCommand());
        add(new RectangleCommand());
        add(new RhombusCommand());
        add(new CircleCommand());
        add(new CircumcircleCommand());
        add(new ExitCommand());
        add(new SortCommand());
        add(new DoubleCommand());
        add(new RectangularTriangleCommand());
        add(new SaveToFileCommand());
    }};

    final public static Map<String, Command> commands =
            commandsList.stream().collect(Collectors.toMap(Command::getName, Function.identity()));

    public CommandManager(InputStream in, PrintStream out) {
        this.inputStream = in;
        this.outputStream = out;
    }

    public void run() {
        var input = new Scanner(inputStream);
        var shapesManager = new ShapesManager(outputStream);

        commands.forEach((_, command) -> command.setOut(outputStream));

        while (input.hasNext()) {
            var line = input.nextLine();
            var arguments = line.split("\\s+");
            if (arguments.length == 0) {
                continue;
            }

            var commandName = arguments[0].toLowerCase();
            var params = Arrays.copyOfRange(arguments, 1, arguments.length);

            var command = commands.get(commandName);
            boolean shouldContinue;
            if (command != null) {
                shouldContinue = command.execute(params, shapesManager);
            } else {
                outputStream.println(AppSettings.global.translations.get(TranslationKey.unknown_command));
                shouldContinue = commands.get("help").execute(new String[0], shapesManager);
            }
            if (!shouldContinue) break;
        }
    }
}
