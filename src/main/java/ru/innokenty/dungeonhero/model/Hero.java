package ru.innokenty.dungeonhero.model;

import java.util.EnumMap;
import java.util.Map;

import static java.lang.Math.max;
import static java.lang.Math.pow;
import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static ru.innokenty.dungeonhero.model.Skill.AGILITY;
import static ru.innokenty.dungeonhero.model.Skill.HEALTH;
import static ru.innokenty.dungeonhero.model.Skill.STRENGTH;
import static ru.innokenty.dungeonhero.model.Skill.VISION;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Hero implements WithVision, Fighter {

    private final Map<Skill, Integer> skills = new EnumMap<>(Skill.class);

    private final Experience experience = new Experience();

    private final String name;

    private int health;

    {
        Skill.forEach(s -> skills.put(s, 1));
        skills.put(VISION, 5);
    }

    public Hero(String playerName) {
        name = playerName;
    }

    public void up(Skill skill) {
        skills.compute(skill, (s, i) -> i == null ? 1 : i + 1);
    }

    public int get(Skill key) {
        return skills.get(key);
    }

    public Experience getExperience() {
        return experience;
    }

    @Override
    public int getVision() {
        return get(VISION);
    }

    public void resetHealth() {
        health = getHealthTotal();
    }

    @Override
    public int getLevel() {
        return getExperience().getLevel();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getHealthTotal() {
        //complex formula that came from my mind and describes how I feel it should work
        return (int) pow((get(HEALTH) * 2 + (double) get(STRENGTH) / 2 + (double) get(AGILITY) / 4) + 2, 2);
    }

    @Override
    public int getMinDamage() {
        //TODO make agility better increase min damage (now level 2 -> 1 - 5)
        //complex formula that came from my mind and describes how I feel it should work
        return (int) round(sqrt(get(STRENGTH) * (1 + pow(max(0, get(AGILITY) - 1), 3))));
    }

    @Override
    public int getMaxDamage() {
        //complex formula that came from my mind and describes how I feel it should work
        return (int) round(sqrt(pow(get(STRENGTH), 2) * (10 + 1.5 * pow(get(AGILITY), 3))));
    }

    @Override
    public void damage(int damage) {
        health = max(health - damage, 0);
    }
}
