package ru.innokenty.dungeonhero.controller.command;

import java.awt.Point;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class MoveUpCommand extends MoveCommand {

    public MoveUpCommand() {
        super('w', "up", "move character up if applicable");
    }

    @Override
    public Point apply(Point point) {
        Point result = new Point(point);
        result.translate(0, -1);
        return result;
    }
}
