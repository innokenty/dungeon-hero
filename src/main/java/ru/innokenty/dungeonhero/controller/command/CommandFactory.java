package ru.innokenty.dungeonhero.controller.command;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class CommandFactory {

    public static Command parse(String commandString) throws UnsupportedCommandException {
        return Command.COMMANDS.stream()
               .filter(c -> c.getKey().equals(commandString))
               .findFirst()
               .orElseThrow(() -> new UnsupportedCommandException(commandString));
    }
}
