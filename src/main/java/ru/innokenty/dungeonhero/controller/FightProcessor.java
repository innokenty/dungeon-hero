package ru.innokenty.dungeonhero.controller;

import ru.innokenty.dungeonhero.model.Fight;
import ru.innokenty.dungeonhero.model.Fighter;
import ru.innokenty.dungeonhero.model.Punch;

import java.util.Random;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class FightProcessor {

    private final Fighter hero;
    private final Fighter monster;

    public FightProcessor(Fight fight) {
        hero = fight.getHero();
        monster = fight.getMonster();
    }

    public Punch hitOnce() {
        return new Punch(hit(hero, monster), hit(monster, hero));
    }

    private int hit(Fighter fighter1, Fighter fighter2) {
        int damage = fighter1.getMinDamage() + new Random().nextInt(fighter1.getMaxDamage());
        fighter2.damage(damage);
        return damage;
    }

    public boolean isOver() {
        return isDead(hero) || isDead(monster);
    }

    private boolean isDead(Fighter fighter) {
        return fighter.getHealth() == 0;
    }

    public boolean heroWins() {
        return isOver() && !isDead(hero);
    }
}
