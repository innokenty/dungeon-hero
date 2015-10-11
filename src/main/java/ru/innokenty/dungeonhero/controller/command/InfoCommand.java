package ru.innokenty.dungeonhero.controller.command;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class InfoCommand extends Command {

    public InfoCommand() {
        super('i', "info", "display character stats and info");
    }
}
