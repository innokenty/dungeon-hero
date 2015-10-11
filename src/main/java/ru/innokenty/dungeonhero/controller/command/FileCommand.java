package ru.innokenty.dungeonhero.controller.command;

import java.io.File;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public abstract class FileCommand extends Command {

    private final String filename;

    public FileCommand(char key, String name, String description, String filename) {
        super(name, description);
        this.filename = filename;
    }

    protected final File getFile() {
        return new File(filename);
    }
}
