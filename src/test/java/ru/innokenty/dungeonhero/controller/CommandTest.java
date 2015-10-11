package ru.innokenty.dungeonhero.controller;

import org.junit.Test;
import ru.innokenty.dungeonhero.controller.command.Command;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static ru.innokenty.dungeonhero.controller.command.Command.COMMANDS;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class CommandTest {

    @Test
    public void testCommandHaveDifferentKeys() {
        assertThat(
                COMMANDS.stream().map(Command::getKey).collect(toSet()).size(),
                is(equalTo(COMMANDS.size()))
        );
    }
}
