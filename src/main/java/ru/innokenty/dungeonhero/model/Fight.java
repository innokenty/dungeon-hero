package ru.innokenty.dungeonhero.model;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Fight {

    private final Hero hero;
    private final Monster monster;

    public Fight(Hero hero, Monster monster) {
        this.hero = hero;
        this.monster = monster;
    }

    public Hero getHero() {
        return hero;
    }

    public Monster getMonster() {
        return monster;
    }
}
