package ru.innokenty.dungeonhero;

import ru.innokenty.dungeonhero.model.RectangularMap;
import ru.innokenty.dungeonhero.model.State;
import ru.innokenty.dungeonhero.view.Output;
import ru.innokenty.dungeonhero.view.console.ConsoleOutput;

import java.io.InputStreamReader;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Main {

    private static RectangularMap map = new RectangularMap(new String[]{
            "+--------------------------+",
            "|s     1                   |",
            "|      +----+              |",
            "|      |....|        1     |",
            "|      |....|              |",
            "|      |....+----+         |",
            "|      |.........|   3     |",
            "|      |.........|         |",
            "|      +---------+         |",
            "|                     5    |",
            "|      1         +         |",
            "+---+ +--------------------+",
            "|...|3|......+------+......|",
            "|...| |......+8  9 f|......|",
            "|...| +-----+  +----+......|",
            "|...| 6    7   |...........|",
            "+---+----------------------+",
    });

    public static void main(String[] args) {
        CommandIterator commandIterator = new ReaderCommandIterator(new InputStreamReader(System.in));
        Output output = new ConsoleOutput();
        State state = new State(map);
        new DungeonHero(state, commandIterator, output).start();
    }
}
