package ru.innokenty.dungeonhero.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import ru.innokenty.dungeonhero.controller.damage.CoNormalDamageDistributionModel;
import ru.innokenty.dungeonhero.controller.damage.DamageDistributionModel;
import ru.innokenty.dungeonhero.controller.damage.NormalDamageDistributionModel;
import ru.innokenty.dungeonhero.controller.damage.UniformDamageDistributionModel;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
@RunWith(Parameterized.class)
public class DamageDistributionModelsTest {

    private static final int TESTS_COUNT = 9999999;

    @Parameters(name = "{3} ({1} - {2})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new UniformDamageDistributionModel(), 2, 7, "uniform"},
                {new NormalDamageDistributionModel(), 2, 7, "normal"},
                {new CoNormalDamageDistributionModel(), 2, 7, "co-normal"},
                {new NormalDamageDistributionModel(), 2, 8, "normal"},
                {new CoNormalDamageDistributionModel(), 2, 8, "co-normal"},
        });
    }

    private final DamageDistributionModel model;

    private final int min;
    private final int max;

    public DamageDistributionModelsTest(DamageDistributionModel model, int min, int max,
                                        @SuppressWarnings("UnusedParameters") String name) {
        this.model = model;
        this.min = min;
        this.max = max;
    }

    @Test
    public void testModel() throws Exception {
        //dmg value -> times occurred
        TreeMap<Integer, Integer> values = new TreeMap<>();
        for (int i = 0; i < TESTS_COUNT; i++) {
            int dmg = model.chooseDamage(min, max);
            values.compute(dmg, (dmg1, count) -> count == null ? 1 : count + 1);
        }

        System.out.println();
        for (Map.Entry<Integer, Integer> entry : values.entrySet()) {
            System.out.println(entry.getKey() + (entry.getKey() < 10 ? " " : "") + " => " + entry.getValue());
        }

        assertThat(values.firstKey(), is(equalTo(min)));
        assertThat(values.lastKey(), is(equalTo(max)));

        while (!values.isEmpty()) {
            Map.Entry<Integer, Integer> firstEntry = values.pollFirstEntry();
            Map.Entry<Integer, Integer> lastEntry = values.pollLastEntry();
            if (lastEntry != null) {
                assertThat((double) firstEntry.getValue() / lastEntry.getValue(),
                        is(both(greaterThan(0.95)).and(lessThan(1.05))));
            }
        }
    }
}
