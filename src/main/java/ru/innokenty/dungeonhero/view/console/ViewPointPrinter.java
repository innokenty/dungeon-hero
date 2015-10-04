package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.model.ViewPoint;
import ru.innokenty.dungeonhero.model.cell.Cell;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class ViewPointPrinter implements Printer<ViewPoint> {

    @Override
    public String stringify(ViewPoint viewPoint) {
        Cell[][] visible = viewPoint.getVisibleArea();
        StringBuilder builder = new StringBuilder();

        int lengthY = visible[0].length;

        for (int row = 0; row < lengthY; row++) {
            for (Cell[] column : visible) {
                builder.append(column[row].toString());
            }
            if (row < lengthY - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }
}
