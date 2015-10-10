package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.model.Cell;
import ru.innokenty.dungeonhero.model.ViewPoint;

import java.util.Arrays;

import static ru.innokenty.dungeonhero.model.Cell.DARK;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class ViewPointPrinter implements Printer<ViewPoint> {

    @Override
    public boolean accept(Object printable) {
        return printable instanceof ViewPoint;
    }

    @Override
    public String print(ViewPoint viewPoint) {
        Cell[][] map = surroundWithDarkness(viewPoint.getVisibleArea());

        int lengthY = map[0].length;

        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < lengthY; row++) {
            for (Cell[] column : map) {
                builder.append(CellPrinter.print(column[row]));
            }
            if (row < lengthY - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }

    private Cell[][] surroundWithDarkness(Cell[][] viewPoint) {
        int lengthX = viewPoint.length;
        int lengthY = viewPoint[0].length;

        Cell[][] result = new Cell[lengthX + 2][lengthY + 2];
        Arrays.fill(result[0], DARK);
        Arrays.fill(result[lengthX + 1], DARK);
        for (int i = 0; i < lengthX + 2; i++) {
            result[i][0] = DARK;
            result[i][lengthY + 1] = DARK;
        }

        for (int i = 0; i < lengthX; i++) {
            System.arraycopy(viewPoint[i], 0, result[i + 1], 1, lengthY);
        }

        return result;
    }
}
