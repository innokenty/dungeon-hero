package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.DungeonHeroException;
import ru.innokenty.dungeonhero.controller.Processor;
import ru.innokenty.dungeonhero.model.State;
import ru.innokenty.dungeonhero.view.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.singletonList;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class LoadGameCommand extends FileCommand {

    public LoadGameCommand(String filename) {
        super("load game", "open previously saved game from a file", filename);
    }

    @Override
    public List<?> handle(Processor processor) {
        File file = getFile();
        try {
            assertCanReadFile(file);
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                processor.setState((State) ois.readObject());
            }
            return singletonList(new Message(format(
                    "Successfully loaded the saved game from %s!", file.getAbsolutePath())));
        } catch (ClassNotFoundException e) {
            throw new DungeonHeroException("Unable read the save file " + file.getAbsolutePath(), e);
        } catch (IOException e) {
            throw new DungeonHeroException("Unable to save the game to file " + file.getAbsolutePath(), e);
        }
    }
}
