package ru.innokenty.dungeonhero.controller;

import java.util.Random;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class UniformDamageDistributionModel implements DamageDistributionModel {

    @Override
    public int chooseDamage(int min, int max) {
        return min + new Random().nextInt(max - min + 1);
    }
}
