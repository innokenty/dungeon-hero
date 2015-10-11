package ru.innokenty.dungeonhero.controller.command;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class HelpCommand extends Command {

    public HelpCommand() {
        super('h', "help", "display this help");
    }
}
