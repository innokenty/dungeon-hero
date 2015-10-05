package ru.innokenty.dungeonhero.view;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Help implements Printable {

    private static final Help instance = new Help();

    public static Help getInstance() {
        return instance;
    }

    private Help() {
    }
}
