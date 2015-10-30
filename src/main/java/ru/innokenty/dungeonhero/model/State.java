package ru.innokenty.dungeonhero.model;

import ru.innokenty.dungeonhero.controller.damage.CoNormalDamageDistributionModel;
import ru.innokenty.dungeonhero.controller.damage.DamageDistributionModel;

import java.io.Serializable;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class State implements Serializable {

    private Hero hero;
    private final DamageDistributionModel damageModel;

    private final String playerName;

    private ViewPoint viewPoint;
    private Fight fight;

    private boolean levelUp;

    public State(String playerName) {
        this.playerName = playerName;
        this.damageModel = new CoNormalDamageDistributionModel();
        resetHero(); //TODO init hero only when a map is loaded
    }

    public void resetHero() {
        hero = new Hero(this.playerName);
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
