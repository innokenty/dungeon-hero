package ru.innokenty.dungeonhero.model;

import ru.innokenty.dungeonhero.controller.damage.DamageDistributionModel;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public interface Fighter {

    String getName();
    int getLevel();
    int getHealth();
    int getHealthTotal();
    int getMinDamage();
    int getMaxDamage();
    void damage(int damage);

    default int hit(Fighter target, DamageDistributionModel damageModel) {
        int damage = damageModel.chooseDamage(getMinDamage(), getMaxDamage());
        target.damage(damage);
        return damage;
    }
}
