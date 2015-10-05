package ru.innokenty.dungeonhero.model.cell;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class MonsterCell extends Cell {

    public MonsterCell(char code) {
        super(code);
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    public int getLevel() {
        return Integer.parseInt(String.valueOf(getCode()));
    }
}
