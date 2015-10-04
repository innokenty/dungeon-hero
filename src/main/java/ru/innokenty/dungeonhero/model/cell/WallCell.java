package ru.innokenty.dungeonhero.model.cell;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class WallCell extends Cell {

    public WallCell(char code) {
        super(code);
    }

    @Override
    public boolean isAccessible() {
        return false;
    }
}
