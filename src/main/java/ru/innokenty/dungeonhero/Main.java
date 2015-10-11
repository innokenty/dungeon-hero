package ru.innokenty.dungeonhero;

import ru.innokenty.dungeonhero.input.ReaderCommandIterator;
import ru.innokenty.dungeonhero.input.RectangularMapFileReader;
import ru.innokenty.dungeonhero.model.State;
import ru.innokenty.dungeonhero.view.console.ConsoleOutput;

import java.io.InputStreamReader;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Main {

    public static void main(String[] args) {
        new DungeonHero(
                new State(
                        new RectangularMapFileReader(args[0]).getMap(),
                        args.length > 1 ? args[1] : "Player"),
                new ReaderCommandIterator(new InputStreamReader(System.in)),
                new ConsoleOutput()
        ).start();
    }
}
