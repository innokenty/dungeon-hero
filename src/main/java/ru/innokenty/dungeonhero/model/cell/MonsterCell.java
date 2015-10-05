package ru.innokenty.dungeonhero.model.cell;

/**
 * //TODO implement
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
}
