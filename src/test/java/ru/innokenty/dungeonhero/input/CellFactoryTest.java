package ru.innokenty.dungeonhero.input;

import org.junit.Test;
import ru.innokenty.dungeonhero.model.Cell;
import ru.innokenty.dungeonhero.model.MonsterCell;
import ru.innokenty.dungeonhero.model.WallCell;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ru.innokenty.dungeonhero.input.CellFactory.parse;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class CellFactoryTest {

    @Test
    public void testParse() throws Exception {
        assertThat(parse(' '), is(instanceOf(Cell.Empty.class)));
        assertThat(parse('s'), is(instanceOf(Cell.Start.class)));
        assertThat(parse('@'), is(instanceOf(Cell.Finish.class)));
        assertThat(parse('.'), is(instanceOf(Cell.Dark.class)));

        assertThat(parse('+'), instanceOf(WallCell.class));
        assertThat(parse('-'), instanceOf(WallCell.class));
        assertThat(parse('|'), instanceOf(WallCell.class));

        for (int i = 0; i < 10; i++) {
            assertThat(parse(String.valueOf(i).charAt(0)), instanceOf(MonsterCell.class));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionOnUnknownChar() {
        parse('g');
    }
}
