package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.view.Help;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class ConsoleOutput extends PrintStreamOutput {

    public ConsoleOutput() {
        super(System.out, System.err);
        output(Help.getInstance());
    }
}
