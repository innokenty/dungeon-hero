package ru.innokenty.dungeonhero.view.console;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public interface Printer<T> {
    String stringify(T t);
}
