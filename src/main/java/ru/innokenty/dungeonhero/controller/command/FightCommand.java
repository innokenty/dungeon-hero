package ru.innokenty.dungeonhero.controller.command;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class FightCommand extends Command {

    public FightCommand() {
        super('f', "fight info", "display the current fight info");
    }
}
