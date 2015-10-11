package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.DungeonHeroException;
import ru.innokenty.dungeonhero.controller.Processor;
import ru.innokenty.dungeonhero.view.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.singletonList;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class SaveGameCommand extends Command {

    public SaveGameCommand() {
        super('k', "save", "save the game to a file");
    }

    @Override
    public List<?> handle(Processor processor) {
        File file = new File("doc/saves/saved_game.dhs");
        try {
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                throw new DungeonHeroException(format(
                        "Unable to create directory at %s!", file.getParentFile().getAbsolutePath()));
            }
            if (file.exists() && !file.canWrite()) {
                throw new DungeonHeroException(format(
                        "Unable to write to the file at %s!", file.getAbsolutePath()));
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(processor.getState());
            }
            return singletonList(new Message(format(
                    "Successfully saved game to file %s!", file.getAbsolutePath())));
        } catch (IOException e) {
            throw new DungeonHeroException(format(
                    "Unable to save the game to file %s!", file.getAbsolutePath()), e);
        }
    }
}
