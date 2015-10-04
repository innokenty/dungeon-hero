package ru.innokenty.dungeonhero.model.cell;

/**
 * //TODO remove hierarchy?
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public abstract class Cell {

    private final char code;

    public Cell(char code) {
        this.code = code;
    }

    public boolean isAccessible() {
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }
}
