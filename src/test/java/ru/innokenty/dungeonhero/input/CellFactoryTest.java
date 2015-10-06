package ru.innokenty.dungeonhero.input;

import org.junit.Test;
import ru.innokenty.dungeonhero.model.MonsterCell;
import ru.innokenty.dungeonhero.model.WallCell;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static ru.innokenty.dungeonhero.input.CellFactory.parse;
import static ru.innokenty.dungeonhero.model.Cell.DARK;
import static ru.innokenty.dungeonhero.model.Cell.EMPTY;
import static ru.innokenty.dungeonhero.model.Cell.FINISH;
import static ru.innokenty.dungeonhero.model.Cell.START;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class CellFactoryTest {

    @Test
    public void testParse() throws Exception {
        assertThat(parse(' '), equalTo(EMPTY));
        assertThat(parse('s'), equalTo(START));
        assertThat(parse('@'), equalTo(FINISH));
        assertThat(parse('.'), equalTo(DARK));

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
