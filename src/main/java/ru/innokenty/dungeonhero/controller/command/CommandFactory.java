package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.DungeonHeroException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;

import static java.util.Collections.singletonList;
import static java.util.regex.Pattern.compile;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class CommandFactory {

    /** char -> command */
    public static final HashMap<Character, Command> SIMPLE_COMMANDS = new LinkedHashMap<>();

    /** regex -> command builder */
    public static final HashMap<String, Function<String, Command>> COMPLEX_COMMANDS = new HashMap<>();

    static {
        SIMPLE_COMMANDS.put('h', new HelpCommand());
        SIMPLE_COMMANDS.put('i', new InfoCommand());
        SIMPLE_COMMANDS.put('m', new MapCommand());
        SIMPLE_COMMANDS.put('s', new MoveDownCommand());
        SIMPLE_COMMANDS.put('a', new MoveLeftCommand());
        SIMPLE_COMMANDS.put('d', new MoveRightCommand());
        SIMPLE_COMMANDS.put('w', new MoveUpCommand());
        SIMPLE_COMMANDS.put('f', new FightInfoCommand());
        SIMPLE_COMMANDS.put('p', new PunchCommand());
        SIMPLE_COMMANDS.put('x', new IncreaseAgilityCommand());
        SIMPLE_COMMANDS.put('c', new IncreaseHealthCommand());
        SIMPLE_COMMANDS.put('z', new IncreaseStrengthCommand());
        SIMPLE_COMMANDS.put('v', new IncreaseVisionCommand());
        SIMPLE_COMMANDS.put('q', new QuitCommand());

        COMPLEX_COMMANDS.put("lm (.+)", LoadMapCommand::new);
        COMPLEX_COMMANDS.put("gl (.+)", LoadGameCommand::new);
        COMPLEX_COMMANDS.put("gs (.+)", SaveGameCommand::new);
    }

    public static List<Command> parse(String commandString) {
        if (SIMPLE_COMMANDS.containsKey(commandString.charAt(0))) {
            return parseSimpleCommands(commandString);
        } else {
            return singletonList(parseComplexCommand(commandString));
        }
    }

    private static List<Command> parseSimpleCommands(String commandString) throws UnsupportedCommandException {
        List<Command> result = new ArrayList<>();
        for (char c : commandString.toCharArray()) {
            Command command = SIMPLE_COMMANDS.get(c);
            if (command == null) {
                throw new UnsupportedCommandException(String.valueOf(c));
            }
            result.add(command);
        }
        return result;
    }

    private static Command parseComplexCommand(String commandString) {
        String key = COMPLEX_COMMANDS.keySet().stream()
                .filter(commandString::matches)
                .findFirst()
                .orElseThrow(() -> new UnsupportedCommandException(commandString));

        Matcher matcher = compile(key).matcher(commandString);
        if (matcher.find()) {
            String arg = matcher.group(1);
            return COMPLEX_COMMANDS.get(key).apply(arg);
        }

        throw new DungeonHeroException(String.format(
                "Could not match pattern '%s' against '%s'. Something wrong with the code, this can not happen.",
                key, commandString));
    }

    private static class UnsupportedCommandException extends DungeonHeroException {
        public UnsupportedCommandException(String command) {
            super("Command '" + command + "' not supported!");
        }
    }
}
