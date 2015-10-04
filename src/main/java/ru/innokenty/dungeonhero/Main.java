package ru.innokenty.dungeonhero;

import java.util.Scanner;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Main {

    public static void main(String[] args) {
        CommandIterator commandIterator = new ScannerCommandIterator(new Scanner(System.in));
        new DungeonHero(commandIterator).start();
    }
}
