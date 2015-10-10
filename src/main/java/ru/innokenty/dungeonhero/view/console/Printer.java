package ru.innokenty.dungeonhero.view.console;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public interface Printer<T> {

    boolean accept(Object printable);

    String print(T printable);
}
