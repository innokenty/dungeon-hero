package ru.innokenty.dungeonhero.model;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class State {

    private final Hero hero;
    private final ViewPoint viewPoint;

    public State(WorldMap map) {
        this.hero = new Hero();
        this.viewPoint = new ViewPoint(map, hero);
    }

    public Hero getHero() {
        return hero;
    }

    public ViewPoint getViewPoint() {
        return viewPoint;
    }
}
