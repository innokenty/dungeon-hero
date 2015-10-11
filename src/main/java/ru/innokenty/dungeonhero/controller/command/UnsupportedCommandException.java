package ru.innokenty.dungeonhero.controller.command;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class UnsupportedCommandException extends Exception {

    private final String command;

    public UnsupportedCommandException(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
