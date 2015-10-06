package ru.innokenty.dungeonhero.model;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class State {

    private final Hero hero;
    private final ViewPoint viewPoint;

    private Fight fight;

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

    public Fight getFight() {
        return fight;
    }

    public boolean isInFight() {
        return fight != null;
    }

    public void startFightWith(Monster monster) {
        this.fight = new Fight(hero, monster);
    }

    public void stopFight() {
        this.fight = null;
    }
}
