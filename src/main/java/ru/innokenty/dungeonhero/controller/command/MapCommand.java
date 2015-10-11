package ru.innokenty.dungeonhero.controller.command;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class MapCommand extends Command {

    public MapCommand() {
        super('m', "map", "display the currently visible portion of the map");
    }
}
