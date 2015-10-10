package ru.innokenty.dungeonhero.controller;

import java.util.Random;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class EvenDamageDistributionModel implements DamageDistributionModel {

    @Override
    public int chooseDamage(int min, int max) {
        int diff = max - min;
        return min + (diff > 0 ? new Random().nextInt(diff) : 0);
    }
}
