package ru.innokenty.dungeonhero.model;

import ru.innokenty.dungeonhero.controller.CoNormalDamageDistributionModel;
import ru.innokenty.dungeonhero.controller.DamageDistributionModel;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class State {

    private final Hero hero;
    private final ViewPoint viewPoint;
    private final DamageDistributionModel damageModel;

    private Fight fight;

    private boolean levelUp;

    public State(WorldMap map) {
        hero = new Hero();
        viewPoint = new ViewPoint(map, hero);
        damageModel = new CoNormalDamageDistributionModel();
    }

    public Hero getHero() {
        return hero;
    }

    public ViewPoint getViewPoint() {
        return viewPoint;
    }

    public DamageDistributionModel getDamageModel() {
        return damageModel;
    }

    public Fight getFight() {
        return fight;
    }

    public boolean isInFight() {
        return fight != null;
    }

    public void startFightWith(Monster monster) {
        fight = new Fight(hero, monster);
    }

    public void stopFight() {
        fight = null;
    }

    public boolean isLevelUpPick() {
        return levelUp;
    }

    public void startLevelUpPick() {
        levelUp = true;
    }

    public void finishLevelUpPick() {
        levelUp = false;
    }
}
