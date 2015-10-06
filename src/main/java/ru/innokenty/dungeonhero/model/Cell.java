package ru.innokenty.dungeonhero.model;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Cell {

    public static final Cell EMPTY = new Cell();
    public static final Cell DARK = new Cell();
    public static final Cell START = new Cell();
    public static final Cell HERO = new Cell();
    public static final Cell FINISH = new Cell(true);

    private final boolean interactable;

    protected Cell() {
        this(false);
    }

    public Cell(boolean interactable) {
        this.interactable = interactable;
    }


    public boolean isAccessible() {
        return true;
    }

    public boolean isInteractable() {
        return interactable;
    }
}
