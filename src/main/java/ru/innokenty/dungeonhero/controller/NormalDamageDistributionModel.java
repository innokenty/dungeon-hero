package ru.innokenty.dungeonhero.controller;

import java.io.Serializable;
import java.util.Random;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class NormalDamageDistributionModel implements Serializable, DamageDistributionModel {

    private static final Random RND = new Random();

    private static final int VARIANCE_TO_LENGTH_COEF = 6;

    public int chooseDamage(int min, int max) {
        return (int) Math.round(chooseDamageRaw(min, max));
    }

    protected double chooseDamageRaw(int min, int max) {
        double mean = (double) (max + min) / 2;
        double variance = (double) (max - min) / VARIANCE_TO_LENGTH_COEF;
        double dmg = mean + RND.nextGaussian() * variance;
        if (dmg < min) return min;
        if (dmg > max) return max;
        return dmg;
    }
}
