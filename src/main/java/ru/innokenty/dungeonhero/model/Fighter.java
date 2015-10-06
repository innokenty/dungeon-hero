package ru.innokenty.dungeonhero.model;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public interface Fighter {

    String getName();
    int getHealth();
    int getHealthTotal();
    int getMinDamage();
    int getMaxDamage();
    void damage(int damage);
}
