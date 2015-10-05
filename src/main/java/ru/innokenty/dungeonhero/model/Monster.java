package ru.innokenty.dungeonhero.model;

import java.util.Random;

import static java.lang.Math.pow;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Monster implements Fighter {

    private static final String[] NAMES = new String[]{
            "Evil Pig",
            "Horny Cougar", //if you know what I mean
            "Pink Kangaroo",
            "Giant Platypus",
            "Ant-tiger",
            "Hungry Anteater",
            "Psychopathic Grandma",
            "All-devouring Randomness of Existence"
    };

    private final int healthTotal;
    private final int health;
    private final int minHitDamage;
    private final int maxHitDamage;
    private String name;

    public Monster(int level) {
        Random random = new Random();
        this.name = NAMES[random.nextInt(NAMES.length)];
        this.healthTotal = level * 10 + random.nextInt((int) pow(level, 2));
        this.health = healthTotal;
        this.minHitDamage = level;
        this.maxHitDamage = minHitDamage + random.nextInt((int) pow(level, 2));
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getHealthTotal() {
        return healthTotal;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMinDamage() {
        return minHitDamage;
    }

    @Override
    public int getMaxDamage() {
        return maxHitDamage;
    }
}
