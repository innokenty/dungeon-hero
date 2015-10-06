package ru.innokenty.dungeonhero.model;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class MonsterCell extends Cell {

    private final int level;

    public MonsterCell(char code) {
        level = Integer.parseInt(String.valueOf(code));
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    public int getLevel() {
        return level;
    }
}
