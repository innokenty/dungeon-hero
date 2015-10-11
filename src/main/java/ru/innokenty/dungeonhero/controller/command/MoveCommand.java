package ru.innokenty.dungeonhero.controller.command;

import java.awt.Point;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public abstract class MoveCommand extends Command {

    public MoveCommand(char key, String name, String description) {
        super(key, name, description);
    }

    public abstract Point apply(Point point);
}
