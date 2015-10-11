package ru.innokenty.dungeonhero.controller.command;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class QuitCommand extends Command {

    public QuitCommand() {
        super('q', "quit", "quit the game without saving");
    }
}
