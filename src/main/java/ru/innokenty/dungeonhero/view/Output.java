package ru.innokenty.dungeonhero.view;

import ru.innokenty.dungeonhero.controller.UnsupportedCommandException;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public interface Output {
    void output(Printable printable);
    void outputException(UnsupportedCommandException e);
}
