package ru.innokenty.dungeonhero.model;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Punch {

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
