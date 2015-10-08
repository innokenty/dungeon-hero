package ru.innokenty.dungeonhero.input;

import ru.innokenty.dungeonhero.DungeonHeroException;
import ru.innokenty.dungeonhero.controller.Command;
import ru.innokenty.dungeonhero.controller.UnsupportedCommandException;

import java.io.IOException;
import java.io.Reader;

public class ReaderCommandIterator implements CommandIterator {

    private final Reader reader;

    public ReaderCommandIterator(Reader reader) {
        this.reader = reader;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Command next() throws UnsupportedCommandException {
        return Command.parse(nextLine());
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