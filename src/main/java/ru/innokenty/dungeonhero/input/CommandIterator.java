package ru.innokenty.dungeonhero.input;

import ru.innokenty.dungeonhero.controller.command.Command;
import ru.innokenty.dungeonhero.controller.command.UnsupportedCommandException;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public interface CommandIterator {

    boolean hasNext();

    Command next() throws UnsupportedCommandException;
}
