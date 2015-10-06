package ru.innokenty.dungeonhero.model;

import ru.innokenty.dungeonhero.view.Printable;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Punch implements Printable {

    private final int heroDamage;
    private final int monsterDamage;

    public Punch(int heroDamage, int monsterDamage) {
        this.heroDamage = heroDamage;
        this.monsterDamage = monsterDamage;
    }

    public int getHeroDamage() {
        return heroDamage;
    }

    public int getMonsterDamage() {
        return monsterDamage;
    }
}
