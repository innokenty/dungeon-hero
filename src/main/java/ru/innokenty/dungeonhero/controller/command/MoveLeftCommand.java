package ru.innokenty.dungeonhero.controller.command;

import java.awt.Point;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class MoveLeftCommand extends MoveCommand {

    public MoveLeftCommand() {
        super('a', "left", "move character left if applicable");
    }

    @Override
    public Point apply(Point point) {
        Point result = new Point(point);
        result.translate(-1, 0);
        return result;
    }
}
