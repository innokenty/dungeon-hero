package ru.innokenty.dungeonhero.view;

import ru.innokenty.dungeonhero.controller.command.UnsupportedCommandException;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public interface Output {
    void output(Object printable);
    void outputException(UnsupportedCommandException e);
}
