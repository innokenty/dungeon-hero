package ru.innokenty.dungeonhero.model;

import ru.innokenty.dungeonhero.controller.EvenDamageDistributionModel;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public interface Fighter {

    int getLevel();
    String getName();
    int getHealth();
    int getHealthTotal();
    int getMinDamage();
    int getMaxDamage();
    void damage(int damage);

    default int hit(Fighter target, EvenDamageDistributionModel damageModel) {
        int damage = damageModel.chooseDamage(getMinDamage(), getMaxDamage());
        target.damage(damage);
        return damage;
    }
}
