package ru.innokenty.dungeonhero.model;

import ru.innokenty.dungeonhero.controller.damage.CoNormalDamageDistributionModel;
import ru.innokenty.dungeonhero.controller.damage.DamageDistributionModel;

import java.io.Serializable;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class State implements Serializable {

    private final Hero hero;
    private final DamageDistributionModel damageModel;

    private ViewPoint viewPoint;
    private Fight fight;

    private boolean levelUp;

    public State(String playerName) {
        hero = new Hero(playerName);
        damageModel = new CoNormalDamageDistributionModel();
    }

    public Hero getHero() {
        return hero;
    }

    public DamageDistributionModel getDamageModel() {
        return damageModel;
    }

    public ViewPoint getViewPoint() {
        return viewPoint;
    }

    public boolean isMapLoaded() {
        return getViewPoint() != null;
    }

    public void setMap(WorldMap map) {
        viewPoint = new ViewPoint(map, hero);
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
