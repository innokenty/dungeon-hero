package ru.innokenty.dungeonhero.model.cell;

import ru.innokenty.dungeonhero.model.EmptyCell;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class CellFactory {

    private CellFactory() {
    }

    public static Cell parse(char cellChar) {
        String cell = String.valueOf(cellChar);
        if (cell.equals(" ")) {
            return new EmptyCell(cellChar);
        } else if (cell.equals("s")) {
            return new StartingCell();
        } else if (cell.equals("f")) {
            return new FinishCell(cellChar);
        } else if (cell.matches("[-+|]")) {
            return new WallCell(cellChar);
        } else if (cell.matches("\\d+")) {
            return new MonsterCell(cellChar);
        } else if (cell.matches("\\.")) {
            return new DarkCell();
        }
        throw new IllegalArgumentException("Character '" + cellChar + "' is not supported as a cell descriptor");
    }
}
