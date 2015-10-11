package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.DungeonHeroException;
import ru.innokenty.dungeonhero.controller.Processor;
import ru.innokenty.dungeonhero.model.State;
import ru.innokenty.dungeonhero.view.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class LoadGameCommand extends Command {

    public LoadGameCommand() {
        super('l', "load", "open previously saved game from a file");
    }

    @Override
    public List<?> handle(Processor processor) {
        File file = new File(Processor.FILENAME);
        try {
            if (!file.exists() || !file.canRead()) {
                throw new DungeonHeroException(format(
                        "Unable read the file at %s! Does it exist?", file.getAbsolutePath()));
            }
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                processor.setState((State) ois.readObject());
            }
            return Arrays.asList(
                    new Message(format("Successfully loaded the saved game from %s!", file.getAbsolutePath())),
                    processor.getState().getViewPoint());
        } catch (ClassNotFoundException e) {
            throw new DungeonHeroException("Unable read the save file " + file.getAbsolutePath(), e);
        } catch (IOException e) {
            throw new DungeonHeroException("Unable to save the game to file " + file.getAbsolutePath(), e);
        }
    }
}
