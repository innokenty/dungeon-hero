package ru.innokenty.dungeonhero;

import ru.innokenty.dungeonhero.controller.Command;
import ru.innokenty.dungeonhero.controller.UnsupportedCommandException;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public interface CommandIterator {

    boolean hasNext();

    Command next() throws UnsupportedCommandException;
}
