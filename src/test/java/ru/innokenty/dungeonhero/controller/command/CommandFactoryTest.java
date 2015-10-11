package ru.innokenty.dungeonhero.controller.command;

import org.junit.Test;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ru.innokenty.dungeonhero.controller.command.CommandFactory.SIMPLE_COMMANDS;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class CommandFactoryTest {

    @Test
    public void testCommandHaveDifferentKeys() {
        assertThat(
                SIMPLE_COMMANDS.keySet().stream().collect(toSet()).size(),
                is(equalTo(SIMPLE_COMMANDS.size()))
        );
    }
}
