package ru.innokenty.dungeonhero.controller;

import ru.innokenty.dungeonhero.model.Experience;
import ru.innokenty.dungeonhero.model.Fight;
import ru.innokenty.dungeonhero.model.Fighter;
import ru.innokenty.dungeonhero.model.Punch;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class FightProcessor {

    private final Fighter hero;
    private final Fighter monster;

    private final DamageDistributionModel damageModel;

    public FightProcessor(Fight fight, DamageDistributionModel damageModel) {
        hero = fight.getHero();
        monster = fight.getMonster();
        this.damageModel = damageModel;
    }

    public Punch hitOnce() {
        return new Punch(
                hero.hit(monster, damageModel),
                monster.hit(hero, damageModel)
        );
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
                * monster.getLevel() / hero.getLevel()
                * (1 + Math.random() / 2));
    }
}
