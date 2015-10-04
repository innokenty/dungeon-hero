package ru.innokenty.dungeonhero;

import java.util.Arrays;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public enum Command {
    QUIT('q', "quit");

    private final String key;
    private final String name;

    Command(char key, String name) {
        this.key = String.valueOf(key);
        this.name = name;
    }

    public static Command parse(String commandString) throws UnsupportedCommandException {
        return Arrays.asList(Command.values()).stream()
                     .filter(c -> c.key.equals(commandString) || c.name.equals(commandString))
                     .findFirst()
                     .orElseThrow(() -> new UnsupportedCommandException(commandString));
    }
}
