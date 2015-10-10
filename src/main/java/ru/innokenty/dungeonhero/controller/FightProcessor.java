package ru.innokenty.dungeonhero.controller;

import ru.innokenty.dungeonhero.model.Experience;
import ru.innokenty.dungeonhero.model.Fight;
import ru.innokenty.dungeonhero.model.Fighter;
import ru.innokenty.dungeonhero.model.Punch;

import java.util.Random;

import static java.lang.Math.pow;

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
        int diff = fighter1.getMaxDamage() - fighter1.getMinDamage();
        int damage = fighter1.getMinDamage() + (diff > 0 ? new Random().nextInt(diff) : 0);
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

    public int getExperience() {
        return (int) (Experience.SCALE_FACTOR
                * monster.getLevel()
                * pow(monster.getLevel() / hero.getLevel(), 3/2)
                * (1 + Math.random() / 2));
    }
}
