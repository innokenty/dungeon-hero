package ru.innokenty.dungeonhero.view.console;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class ConsoleOutput extends PrintStreamOutput {

    public ConsoleOutput() {
        super(System.out, System.err);
    }
}
