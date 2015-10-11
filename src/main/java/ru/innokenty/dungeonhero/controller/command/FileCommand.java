package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.DungeonHeroException;

import java.io.File;

import static java.lang.String.format;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public abstract class FileCommand extends Command {

    private final String filename;

    public FileCommand(String name, String description, String filename) {
        super(name, description);
        this.filename = filename;
    }

    protected static void assertCanReadFile(File file) {
        if (!file.exists() || !file.canRead()) {
            throw new DungeonHeroException(format(
                    "Unable read the file at %s! Does it exist?", file.getAbsolutePath()));
        }
    }

    protected final File getFile() {
        return new File(filename);
    }
}
