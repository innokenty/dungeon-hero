package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.model.Cell;
import ru.innokenty.dungeonhero.model.ViewPoint;

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
        Cell[][] visible = viewPoint.getVisibleArea();
        StringBuilder builder = new StringBuilder();

        int lengthY = visible[0].length;

        for (int row = 0; row < lengthY; row++) {
            for (Cell[] column : visible) {
                builder.append(CellPrinter.print(column[row]));
            }
            if (row < lengthY - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }
}
