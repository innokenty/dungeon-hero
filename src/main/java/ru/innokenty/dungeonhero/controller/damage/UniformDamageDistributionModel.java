package ru.innokenty.dungeonhero.controller.damage;

import java.io.Serializable;
import java.util.Random;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class UniformDamageDistributionModel implements Serializable, DamageDistributionModel {

    @Override
    public int chooseDamage(int min, int max) {
        return min + new Random().nextInt(max - min + 1);
    }
}
