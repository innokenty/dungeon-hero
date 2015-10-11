package ru.innokenty.dungeonhero.controller.command;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class PunchCommand extends Command {

    public PunchCommand() {
        super('p', "punch", "hit the monster while fighting");
    }
}
