package ru.innokenty.dungeonhero.model;

import java.awt.Point;
import java.util.Arrays;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class RectangularMap implements WorldMap {

    private final Cell[][] cells;

    private final Point startingPoint;

    public RectangularMap(Cell[][] cells, Point startingPoint) {
        this.cells = cells;
        this.startingPoint = startingPoint;
    }

    @Override
    public Point getStartingPoint() {
        return startingPoint;
    }

    @Override
    public int getSizeX() {
        return cells.length;
    }

    @Override
    public int getSizeY() {
        return cells[0].length;
    }

    @Override
    public Cell getCell(Point cell) {
        if (cell.x < 0 || cell.y < 0 || cell.x >= getSizeX() || cell.y >= getSizeY()) {
            return null;
        }
        return cells[cell.x][cell.y];
    }

    @Override
    public void setCell(Point location, Cell cell) {
        cells[location.x][location.y] = cell;
    }

    @Override
    public Cell[][] getSubMap(int fromX, int toX, int fromY, int toY) {
        Cell[][] result = new Cell[toX - fromX + 1][];
        for (int i = 0; i < result.length; i++) {
            result[i] = Arrays.copyOfRange(cells[fromX + i], fromY, toY + 1);
        }
        return result;
    }
}
