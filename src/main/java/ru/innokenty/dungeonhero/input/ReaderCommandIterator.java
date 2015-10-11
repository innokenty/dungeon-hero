package ru.innokenty.dungeonhero.input;

import ru.innokenty.dungeonhero.DungeonHeroException;
import ru.innokenty.dungeonhero.controller.command.Command;
import ru.innokenty.dungeonhero.controller.command.CommandFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ReaderCommandIterator implements Iterator<Command> {

    private final BufferedReader reader;

    private final Queue<Command> commands = new LinkedList<>();

    public ReaderCommandIterator(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Command next() {
        if (commands.isEmpty()) {
            commands.addAll(CommandFactory.parse(nextLine()));
        }
        return commands.poll();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public String nextLine() {
        String next;
        try {
            while ((next = reader.readLine().trim()).isEmpty());
        } catch (IOException e) {
            throw new DungeonHeroException("Unable to read input, closing", e);
        }
        return next;
    }
}
