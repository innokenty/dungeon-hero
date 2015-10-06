package ru.innokenty.dungeonhero.model;

import ru.innokenty.dungeonhero.view.Printable;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Fight implements Printable {

    private final Hero hero;
    private final Monster monster;

    public Fight(Hero hero, Monster monster) {
        this.hero = hero;
        this.monster = monster;
    }

    public Fighter getHero() {
        return hero;
    }

    public Fighter getMonster() {
        return monster;
    }
}
