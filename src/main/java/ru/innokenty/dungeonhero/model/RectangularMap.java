package ru.innokenty.dungeonhero.model;

import ru.innokenty.dungeonhero.model.cell.Cell;
import ru.innokenty.dungeonhero.model.cell.CellFactory;
import ru.innokenty.dungeonhero.model.cell.StartingCell;

import java.awt.Point;
import java.util.Arrays;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class RectangularMap implements WorldMap {

    private final Cell[][] cells;

    private final Point startingPoint;

    public RectangularMap(String[] mapData) {
        cells = new Cell[mapData[0].length()][mapData.length];
        Point startingPoint = null;

        for (int i = 0; i < mapData.length; i++) {
            String rowData = mapData[i];
            for (int j = 0; j < rowData.toCharArray().length; j++) {
                char cellData = rowData.toCharArray()[j];
                Cell cell = CellFactory.parse(cellData);
                cells[j][i] = cell;
                if (cell instanceof StartingCell) {
                    startingPoint = new Point(j, i);
                }
            }
        }

        if (startingPoint == null) {
            throw new IllegalArgumentException("The map contains no starting point!");
        } else {
            this.startingPoint = startingPoint;
        }
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
    public Cell[][] getSubMap(int fromX, int toX, int fromY, int toY) {
        Cell[][] result = new Cell[toX - fromX + 1][];
        for (int i = 0; i < result.length; i++) {
            result[i] = Arrays.copyOfRange(cells[fromX + i], fromY, toY + 1);
        }
        return result;
    }
}
