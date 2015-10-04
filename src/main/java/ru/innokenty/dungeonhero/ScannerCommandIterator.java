package ru.innokenty.dungeonhero;

import java.util.Scanner;

public class ScannerCommandIterator implements CommandIterator {

    private final Scanner input;

    private boolean hasNext = true;

    public ScannerCommandIterator(Scanner input) {
        this.input = input;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public Command next() throws UnsupportedCommandException {
        Command command = Command.parse(nextLine());
        hasNext = command != Command.QUIT;
        return command;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public String nextLine() {
        String line;
        while ((line = input.nextLine().trim()).isEmpty());
        return line;
    }
}
