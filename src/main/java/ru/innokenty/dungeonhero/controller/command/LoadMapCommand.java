package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.controller.Processor;
import ru.innokenty.dungeonhero.input.RectangularMapFileReader;
import ru.innokenty.dungeonhero.view.Message;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class LoadMapCommand extends FileCommand {

    public LoadMapCommand(String filename) {
        super("load map", "load map from a file", filename);
    }

    @Override
    public List<?> handle(Processor processor) {
        File file = getFile();
        assertCanReadFile(file);
        processor.getState().setMap(new RectangularMapFileReader(file).getMap());
        return Arrays.asList(
                new Message(format("Successfully loaded the map from %s!", file.getAbsolutePath())),
                processor.getState().getViewPoint());
    }
}
