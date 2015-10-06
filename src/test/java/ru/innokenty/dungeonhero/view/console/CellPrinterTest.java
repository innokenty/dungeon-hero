package ru.innokenty.dungeonhero.view.console;

import org.junit.Test;
import ru.innokenty.dungeonhero.model.Cell;
import ru.innokenty.dungeonhero.model.MonsterCell;
import ru.innokenty.dungeonhero.model.WallCell;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ru.innokenty.dungeonhero.model.Cell.*;
import static ru.innokenty.dungeonhero.view.console.CellPrinter.print;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class CellPrinterTest {

    @Test
    public void testPrint() throws Exception {
        assertThat(print(EMPTY), is(equalTo(' ')));
        assertThat(print(START), is(equalTo(' ')));
        assertThat(print(DARK), is(equalTo('.')));
        assertThat(print(FINISH), is(equalTo('@')));
        assertThat(print(HERO), is(equalTo('o')));

        assertThat(print(new WallCell('+')), is(equalTo('+')));
        assertThat(print(new WallCell('-')), is(equalTo('-')));
        assertThat(print(new WallCell('|')), is(equalTo('|')));

        for (int i = 0; i < 10; i++) {
            char c = String.valueOf(i).charAt(0);
            assertThat(print(new MonsterCell(c)), is(equalTo(c)));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionOnUnknownChar() {
        print(new TestCell());
    }

    private class TestCell extends Cell{
    }
}
