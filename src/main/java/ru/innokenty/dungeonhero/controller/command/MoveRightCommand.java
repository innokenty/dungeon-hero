package ru.innokenty.dungeonhero.controller.command;

import java.awt.Point;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class MoveRightCommand extends MoveCommand {

    public MoveRightCommand() {
        super('d', "right", "move character right if applicable");
    }


    @Override
    public Point apply(Point point) {
        Point result = new Point(point);
        result.translate(1, 0);
        return result;
    }
}
