package ru.innokenty.dungeonhero.input;

import ru.innokenty.dungeonhero.DungeonHeroException;
import ru.innokenty.dungeonhero.model.RectangularMap;
import ru.innokenty.dungeonhero.model.WorldMap;
import ru.innokenty.dungeonhero.model.cell.Cell;
import ru.innokenty.dungeonhero.model.cell.CellFactory;
import ru.innokenty.dungeonhero.model.cell.StartingCell;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class RectangularMapFileReader {

    private final String file;

    private RectangularMap map;

    public RectangularMapFileReader(String file) {
        this.file = file;
    }

    public WorldMap getMap() {
        if (map == null) {
            map = buildMap();
        }
        return map;
    }

    private RectangularMap buildMap() {
        List<String> lines = readFile();

        Cell [][] cells = new Cell[lines.get(0).length()][lines.size()];
        Point startingPoint = null;

        for (int i = 0; i < lines.size(); i++) {
            String rowData = lines.get(i);
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
        }

        return new RectangularMap(cells, startingPoint);
    }

    private List<String> readFile() {
        List<String> mapData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                mapData.add(line);
            }
        } catch (Exception e) {
            throw new DungeonHeroException("Unable to load map from file '" + file + "'", e);
        }
        return mapData;
    }
}
