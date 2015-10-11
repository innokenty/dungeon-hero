package ru.innokenty.dungeonhero.controller.command;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class SaveGameCommand extends Command {

    public SaveGameCommand() {
        super('k', "save", "save the game to a file");
    }
}
