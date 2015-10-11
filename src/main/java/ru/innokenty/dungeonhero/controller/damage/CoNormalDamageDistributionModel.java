package ru.innokenty.dungeonhero.controller.damage;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class CoNormalDamageDistributionModel extends NormalDamageDistributionModel {

    public int chooseDamage(int min, int max) {
        double mean = (double) (max + min) / 2;
        double radius = (double) (max - min) / 2;
        double dmg = super.chooseDamageRaw(min, max);
        if (dmg < mean) {
            return (int) ceil(dmg + radius);
        } else {
            return (int) floor(dmg - radius);
        }
    }
}
