package ru.innokenty.dungeonhero;

import ru.innokenty.dungeonhero.controller.Processor;
import ru.innokenty.dungeonhero.input.ReaderCommandIterator;
import ru.innokenty.dungeonhero.view.console.ConsoleOutput;

import java.io.InputStreamReader;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class DungeonHero {

    public static void main(String[] args) {
        new Processor(
                args.length > 0 ? args[0] : "Player",
                new ReaderCommandIterator(new InputStreamReader(System.in)),
                new ConsoleOutput()
        ).start();
    }
}
