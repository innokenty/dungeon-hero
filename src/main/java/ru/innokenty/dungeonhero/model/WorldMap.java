package ru.innokenty.dungeonhero.model;

import ru.innokenty.dungeonhero.model.cell.Cell;

import java.awt.Point;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public interface WorldMap {

    Point getStartingPoint();

    int getSizeX();

    int getSizeY();

    Cell getCell(Point cell);

    Cell[][] getSubMap(int fromX, int toX, int fromY, int toY);

    default boolean isAccessible(Point coords) {
        Cell cell = getCell(coords);
        return cell != null && cell.isAccessible();
    }
}
