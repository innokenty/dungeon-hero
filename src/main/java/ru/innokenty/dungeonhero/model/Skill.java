package ru.innokenty.dungeonhero.model;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public enum Skill {
    STRENGTH,
    AGILITY,
    HEALTH,
    VISION;

    @Override
    public String toString() {
        return name().substring(0, 1) + name().substring(1).toLowerCase();
    }

    public static void forEach(Consumer<Skill> action) {
        Arrays.asList(values()).stream().forEach(action);
    }
}
