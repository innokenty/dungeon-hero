package ru.innokenty.dungeonhero;

import ru.innokenty.dungeonhero.controller.Command;
import ru.innokenty.dungeonhero.controller.UnsupportedCommandException;

import java.io.IOException;
import java.io.Reader;

public class ReaderCommandIterator implements CommandIterator {

    private final Reader reader;

    private boolean hasNext = true;

    public ReaderCommandIterator(Reader reader) {
        this.reader = reader;
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
        String next;
        try {
            while ((next = String.valueOf((char) reader.read()).trim()).isEmpty());
        } catch (IOException e) {
            throw new DungeonHeroException("Unable to read input, closing", e);
        }
        return next;
    }
}
