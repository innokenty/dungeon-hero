package ru.innokenty.dungeonhero.model;


/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Experience {

    public static final int SCALE_FACTOR = 1000;

    private int level = 1;
    private int expTotal = 0;
    private int expSinceLastLevel = 0;

    public void gain(int experience) {
        expTotal += experience;
        expSinceLastLevel += experience;
    }

    public boolean levelUp() {
        if (expSinceLastLevel >= getExpForNextLevel()) {
            expSinceLastLevel -= getExpForNextLevel();
            level++;
            return true;
        }
        return false;
    }

    public int getLevel() {
        return level;
    }

    public int getExpTotal() {
        return expTotal;
    }

    public int getExpSinceLastLevel() {
        return expSinceLastLevel;
    }

    public int getExpForNextLevel() {
        return (level + 1) * SCALE_FACTOR;
    }

    public int getExpUntilNextLevel() {
        return getExpForNextLevel() - getExpSinceLastLevel();
    }
}
