package ru.innokenty.dungeonhero;

import ru.innokenty.dungeonhero.model.RectangularMap;
import ru.innokenty.dungeonhero.model.State;
import ru.innokenty.dungeonhero.view.Output;
import ru.innokenty.dungeonhero.view.console.ConsoleOutput;

import java.util.Scanner;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Main {

    private static RectangularMap map = new RectangularMap(new String[]{
            " s                          ",
            "       +----+               ",
            "       |    |        1      ",
            "       |    |               ",
            "       |    +----+          ",
            "       |  2      |   3      ",
            "       |         |          ",
            "       +---------+          ",
            "                            ",
            "       1         +          ",
            "----+3+---------------------",
            "    | |                     ",
            "    | |                     ",
            "    | |                     ",
            "    |f|                     ",
    });

    public static void main(String[] args) {
        CommandIterator commandIterator = new ScannerCommandIterator(new Scanner(System.in));
        Output output = new ConsoleOutput();
        State state = new State(map);
        new DungeonHero(state, commandIterator, output).start();
    }
}
