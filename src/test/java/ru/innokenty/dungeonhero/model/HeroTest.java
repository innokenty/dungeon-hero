package ru.innokenty.dungeonhero.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static ru.innokenty.dungeonhero.model.Skill.AGILITY;
import static ru.innokenty.dungeonhero.model.Skill.HEALTH;
import static ru.innokenty.dungeonhero.model.Skill.STRENGTH;
import static ru.innokenty.dungeonhero.model.Skill.VISION;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class HeroTest {

    private Hero hero;

    @Before
    public void setUp() throws Exception {
        hero = new Hero();
    }

    @Test
    public void testConstructor() {
        Arrays.asList(Skill.values()).stream()
              .forEach(s -> assertThat(hero.get(s), equalTo(1)));
    }

    @Test
    public void testUp() throws Exception {
        hero.up(STRENGTH);
        Skill.forEach(s -> assertThat(hero.get(s), s == STRENGTH ? equalTo(2) : equalTo(1)));
        hero.up(STRENGTH);
        Skill.forEach(s -> assertThat(hero.get(s), s == STRENGTH ? equalTo(3) : equalTo(1)));
        hero.up(AGILITY);
        assertThat(hero.get(STRENGTH), equalTo(3));
        assertThat(hero.get(AGILITY),  equalTo(2));
        assertThat(hero.get(HEALTH),   equalTo(1));
        assertThat(hero.get(VISION),   equalTo(1));
    }
}
