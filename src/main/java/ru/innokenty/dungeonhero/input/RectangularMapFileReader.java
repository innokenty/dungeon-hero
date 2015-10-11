package ru.innokenty.dungeonhero.input;

import ru.innokenty.dungeonhero.DungeonHeroException;
import ru.innokenty.dungeonhero.model.Cell;
import ru.innokenty.dungeonhero.model.RectangularMap;
import ru.innokenty.dungeonhero.model.WorldMap;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static ru.innokenty.dungeonhero.model.Cell.START;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class RectangularMapFileReader {

    private final File file;

    private RectangularMap map;

    public RectangularMapFileReader(File file) {
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
                if (cell == START) {
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
            throw new DungeonHeroException(String.format(
                    "Unable to load map from file '%s'", file.getAbsolutePath()), e);
        }
        return mapData;
    }
}
