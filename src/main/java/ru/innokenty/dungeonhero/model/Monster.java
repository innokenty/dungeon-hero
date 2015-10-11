package ru.innokenty.dungeonhero.model;

import java.io.Serializable;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.pow;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Monster implements Serializable, Fighter {

    private static final String[] NAMES = new String[]{
            "Evil Pig",
            "Horny Cougar", //if you know what I mean
            "Pink Kangaroo",
            "Giant Platypus",
            "Ant-tiger",
            "Hungry Anteater",
            "Psychopathic Grandma",
            "All-devouring Randomness of Existence",
            "Alien nazi invasion from outer space",
            "Robot Godzilla",
            "Monkey armed with a banana",
            "Her Majesty the Queen of England",
            "Bloody succubus",
            "Thirsty beer-addict",
            "Dirty Sanchez",
            "Speedy Gonzalez",
            "Bear with balalayka",
            "Babushka with telezhka"
    };

    private final int level;
    private final int healthTotal;
    private int health;
    private final int minHitDamage;
    private final int maxHitDamage;
    private String name;

    public Monster(int level) {
        Random random = new Random();
        this.level = level;
        this.name = NAMES[random.nextInt(NAMES.length)];
        this.healthTotal = level * 10 + random.nextInt((int) pow(level, 2));
        this.health = healthTotal;
        this.minHitDamage = level;
        this.maxHitDamage = minHitDamage + 1 + random.nextInt((int) pow(level, 2));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLevel() {
        return level;
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
    public int getMinDamage() {
        return minHitDamage;
    }

    @Override
    public int getMaxDamage() {
        return maxHitDamage;
    }

    @Override
    public void damage(int damage) {
        health = max(health - damage, 0);
    }
}
