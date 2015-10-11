package ru.innokenty.dungeonhero.view;

import ru.innokenty.dungeonhero.DungeonHeroException;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public interface Output {
    void output(Object printable);
    void outputException(DungeonHeroException e);
}
