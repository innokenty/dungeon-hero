package ru.innokenty.dungeonhero.model;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class WallCell extends Cell {

    private final char code;

    public WallCell(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    @Override
    public boolean isAccessible() {
        return false;
    }
}
