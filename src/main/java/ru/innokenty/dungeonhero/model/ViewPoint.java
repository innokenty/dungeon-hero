package ru.innokenty.dungeonhero.model;

import java.awt.Point;
import java.util.Collections;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static ru.innokenty.dungeonhero.model.Cell.DARK;
import static ru.innokenty.dungeonhero.model.Cell.EMPTY;
import static ru.innokenty.dungeonhero.model.Cell.HERO;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class ViewPoint {

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

    //TODO move to controller
    public Cell[][] getVisibleArea() {
        int vision = withVision.getVision();

        int fromX = location.x - vision;
        int fromY = location.y - vision;
        int toX = min(location.x + vision, map.getSizeX() - 1);
        int toY = min(location.y + vision, map.getSizeY() - 1);

        int locationX = vision + min(fromX, 0);
        int locationY = vision + min(fromY, 0);

        Cell[][] subMap = map.getSubMap(max(fromX, 0), toX, max(fromY, 0), toY);
        int lengthX = subMap.length;
        int lengthY = subMap[0].length;

        Cell[][] result = new Cell[lengthX + 2][lengthY + 2];
        Collections.nCopies(lengthY + 2, DARK).toArray(result[0]);
        Collections.nCopies(lengthY + 2, DARK).toArray(result[lengthX + 1]);
        for (int i = 0; i < lengthX + 2; i++) {
            result[i][0] = DARK;
            result[i][lengthY + 1] = DARK;
        }

        for (int i = 0; i < lengthX; i++) {
            for (int j = 0; j < lengthY; j++) {
                if (pow(i - locationX, 2) + pow(j - locationY, 2) > pow(vision, 2)) {
                    result[i + 1][j + 1] = DARK;
                } else if (i == locationX && j == locationY) {
                    result[i + 1][j + 1] = HERO;
                } else {
                    result[i + 1][j + 1] = subMap[i][j];
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
