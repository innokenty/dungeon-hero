package ru.innokenty.dungeonhero.controller.command;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class LoadGameCommand extends Command {

    public LoadGameCommand() {
        super('l', "load", "open previously saved game from a file");
    }
}
