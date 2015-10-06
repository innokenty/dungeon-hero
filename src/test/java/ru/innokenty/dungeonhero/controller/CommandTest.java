package ru.innokenty.dungeonhero.controller;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class CommandTest {

    @Test
    public void testCommandHaveDifferentKeys() {
        Set<String> keys = new HashSet<>(Command.values().length);
        Command.forEach(command -> {
            assertThat(keys, not(hasItem(equalTo(command.key))));
            keys.add(command.key);
        });
    }
}
