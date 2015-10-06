package ru.innokenty.dungeonhero.input;

import ru.innokenty.dungeonhero.model.Cell;
import ru.innokenty.dungeonhero.model.MonsterCell;
import ru.innokenty.dungeonhero.model.WallCell;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static ru.innokenty.dungeonhero.model.Cell.DARK;
import static ru.innokenty.dungeonhero.model.Cell.EMPTY;
import static ru.innokenty.dungeonhero.model.Cell.FINISH;
import static ru.innokenty.dungeonhero.model.Cell.START;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class CellFactory {

    private static final Map<Character, Cell> CHARACTER_CELLS = new HashMap<>();

    private static final Map<String, Function<Character, Cell>> REGEX_CELLS = new HashMap<>();

    static {
        CHARACTER_CELLS.put(' ', EMPTY);
        CHARACTER_CELLS.put('s', START);
        CHARACTER_CELLS.put('@', FINISH);
        CHARACTER_CELLS.put('.', DARK);

        REGEX_CELLS.put("[-+|]", WallCell::new);
        REGEX_CELLS.put("\\d",   MonsterCell::new);
    }

    public static Cell parse(char cellChar) {
        Optional<Cell> cell = Optional.ofNullable(CHARACTER_CELLS.get(cellChar));
        if (cell.isPresent()) {
            return cell.get();
        }

        return REGEX_CELLS.entrySet().stream()
                .filter(entry -> String.valueOf(cellChar).matches(entry.getKey()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException(String.format(
                        "Character '%s' is not supported as a cell descriptor", cellChar)))
                .getValue()
                .apply(cellChar);
    }
}
