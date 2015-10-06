package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.model.Cell;
import ru.innokenty.dungeonhero.model.MonsterCell;
import ru.innokenty.dungeonhero.model.WallCell;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static ru.innokenty.dungeonhero.model.Cell.*;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class CellPrinter {

    private static final Map<Cell, Character> CELL_CHARACTERS = new HashMap<>();

    private static final Map<Class<? extends Cell>, Function<Cell, Character>> CELL_PRINTERS = new HashMap<>();

    static {
        CELL_CHARACTERS.put(EMPTY, ' ');
        CELL_CHARACTERS.put(START, ' ');
        CELL_CHARACTERS.put(FINISH, '@');
        CELL_CHARACTERS.put(DARK, '.');
        CELL_CHARACTERS.put(HERO, 'o');

        CELL_PRINTERS.put(WallCell.class, wall -> ((WallCell) wall).getCode());
        CELL_PRINTERS.put(MonsterCell.class, monster -> String.valueOf(((MonsterCell) monster).getLevel()).charAt(0));
    }

    private CellPrinter() {
    }

    public static Character print(Cell cell) {
        Optional<Character> character = Optional.ofNullable(CELL_CHARACTERS.get(cell));
        if (character.isPresent()) {
            return character.get();
        }

        return CELL_PRINTERS.entrySet().stream()
                .filter(entry -> entry.getKey().isAssignableFrom(cell.getClass()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException(String.format(
                        "Unable to print a cell of type '%s'", cell.getClass())))
                .getValue()
                .apply(cell);
    }
}
