package ru.innokenty.dungeonhero.model;

import ru.innokenty.dungeonhero.view.Printable;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

import static ru.innokenty.dungeonhero.model.Skill.VISION;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Hero implements Printable, WithVision {

    private final Map<Skill, Integer> skills = new EnumMap<>(Skill.class);

    private final Experience experience = new Experience();

    {
        Arrays.asList(Skill.values()).stream().forEach(s -> skills.put(s, 1));
        skills.put(VISION, 5);
    }

    public void up(Skill skill) {
        skills.compute(skill, (s, i) -> i == null ? 1 : i + 1);
    }

    public int get(Skill key) {
        return skills.get(key);
    }

    public Experience getExperience() {
        return experience;
    }

    @Override
    public int getVision() {
        return get(VISION);
    }
}
