package ru.innokenty.dungeonhero.model;

import java.io.Serializable;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Cell implements Serializable {

    public static final Cell EMPTY  = new Empty();
    public static final Cell DARK   = new Dark();
    public static final Cell START  = new Start();
    public static final Cell HERO   = new Hero();
    public static final Cell FINISH = new Finish();

    private final boolean interactable;

    protected Cell() {
        this(false);
    }

    protected Cell(boolean interactable) {
        this.interactable = interactable;
    }

    public boolean isAccessible() {
        return true;
    }

    public boolean isInteractable() {
        return interactable;
    }

    public static class Empty extends Cell {}
    public static class Dark extends Cell {}
    public static class Start extends Cell {}
    public static class Hero extends Cell {}
    public static class Finish extends Cell {
        public Finish() {
            super(true);
        }
    }
}
