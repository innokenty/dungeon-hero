package ru.innokenty.dungeonhero.model;

import java.awt.Point;
import java.io.Serializable;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static ru.innokenty.dungeonhero.model.Cell.DARK;
import static ru.innokenty.dungeonhero.model.Cell.EMPTY;
import static ru.innokenty.dungeonhero.model.Cell.HERO;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class ViewPoint implements Serializable {

    private final WorldMap map;
    private final WithVision withVision;
    private final Point location;

    public ViewPoint(WorldMap map, WithVision vision) {
        this.map = map;
        this.withVision = vision;
        this.location = map.getStartingPoint();
    }

    public Point getLocation() {
        return new Point(location);
    }

    public void setLocation(Point location) {
        this.location.setLocation(location);
    }

    public WorldMap getMap() {
        return map;
    }

    public Cell[][] getVisibleArea() {
        int vision = withVision.getVision();

        int fromX = location.x - vision;
        int fromY = location.y - vision;
        int toX = min(location.x + vision, map.getSizeX() - 1);
        int toY = min(location.y + vision, map.getSizeY() - 1);

        int locationX = vision + min(fromX, 0);
        int locationY = vision + min(fromY, 0);

        Cell[][] result = map.getSubMap(max(fromX, 0), toX, max(fromY, 0), toY);
        int lengthX = result.length;
        int lengthY = result[0].length;

        for (int i = 0; i < lengthX; i++) {
            for (int j = 0; j < lengthY; j++) {
                if (pow(i - locationX, 2) + pow(j - locationY, 2) > pow(vision, 2)) {
                    result[i][j] = DARK;
                } else if (i == locationX && j == locationY) {
                    result[i][j] = HERO;
                }
            }
        }

        return result;
    }

    public void replaceCurrentCell(Cell cell) {
        map.setCell(getLocation(), cell);
    }

    public void emptyCurrentCell() {
        replaceCurrentCell(EMPTY);
    }
}
