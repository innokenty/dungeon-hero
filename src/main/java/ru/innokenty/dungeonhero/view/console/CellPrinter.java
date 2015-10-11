package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.model.Cell;
import ru.innokenty.dungeonhero.model.MonsterCell;
import ru.innokenty.dungeonhero.model.WallCell;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class CellPrinter {

    private static final Map<Class<? extends Cell>, Function<Cell, Character>> CELL_PRINTERS = new HashMap<>();

    static {
        CELL_PRINTERS.put(Cell.Empty.class, c -> ' ');
        CELL_PRINTERS.put(Cell.Start.class, c -> ' ');
        CELL_PRINTERS.put(Cell.Finish.class, c -> '@');
        CELL_PRINTERS.put(Cell.Dark.class, c -> '.');
        CELL_PRINTERS.put(Cell.Hero.class, c -> 'o');

        CELL_PRINTERS.put(WallCell.class, wall -> ((WallCell) wall).getCode());
        CELL_PRINTERS.put(MonsterCell.class, monster -> String.valueOf(((MonsterCell) monster).getLevel()).charAt(0));
    }

    private CellPrinter() {
    }

    public static Character print(Cell cell) {
        return CELL_PRINTERS.entrySet().stream()
                .filter(entry -> entry.getKey().isAssignableFrom(cell.getClass()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException(String.format(
                        "Unable to print a cell of type '%s'", cell.getClass())))
                .getValue()
                .apply(cell);
    }
}
