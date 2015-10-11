package ru.innokenty.dungeonhero.model;

import org.junit.Before;
import org.junit.Test;

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
        hero = new Hero("hero");
    }

    @Test
    public void testConstructor() {
        assertSkills(1, 1, 1, 5);
    }

    @Test
    public void testUp() throws Exception {
        hero.up(STRENGTH);
        hero.up(STRENGTH);
        assertSkills(3, 1, 1, 5);
        hero.up(VISION);
        assertSkills(3, 1, 1, 6);
    }

    private void assertSkills(int strength, int agility, int health, int vision) {
        assertThat(hero.get(STRENGTH), equalTo(strength));
        assertThat(hero.get(AGILITY), equalTo(agility));
        assertThat(hero.get(HEALTH), equalTo(health));
        assertThat(hero.get(VISION), equalTo(vision));
    }
}
