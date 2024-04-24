package dev.justpizza.command;

import dev.justpizza.command.list.*;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.triangle.RectangularTriangle;
import dev.justpizza.translations.TranslationKey;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommandManager {
    final private static List<Command> commandsList = new ArrayList<>() {{
        add(new HelpCommand());
        add(new ShapesCommand());
        add(new VersionCommand());
        add(new SquareCommand());
        add(new TriangleCommand());
        add(new EquilateralTriangleCommand());
        add(new IsoscelesTriangleCommand());
        add(new RectangleCommand());
        add(new RhombusCommand());
        add(new CircleCommand());
        add(new CircumcircleCommand());
        add(new ExitCommand());
        add(new SortCommand());
        add(new DoubleCommand());
        add(new RectangularTriangleCommand());
    }};

    final public static Map<String, Command> commands =
            commandsList.stream().collect(Collectors.toMap(Command::getName, Function.identity()));

    public void run() {
        var input = new Scanner(System.in);

        while (input.hasNext()) {
            var line = input.nextLine();
            var arguments = line.split("\\s+");
            if (arguments.length == 0) {
                continue;
            }

            var commandName = arguments[0].toLowerCase();
            var params = Arrays.copyOfRange(arguments, 1, arguments.length);

            var command = commands.get(commandName);
            if (command != null) {
                command.execute(params);
            } else {
                System.out.println(AppSettings.global.translations.get(TranslationKey.unknown_command));
                commands.get("help").execute(new String[0]);
            }
        }
    }
}
