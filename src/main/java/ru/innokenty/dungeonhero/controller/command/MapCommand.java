package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.controller.Processor;
import ru.innokenty.dungeonhero.view.Message;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class MapCommand extends Command {

    public MapCommand() {
        super("map", "display the currently visible portion of the map");
    }

    @Override
    public List<?> handle(Processor processor) {
        return singletonList(processor.getState().isMapLoaded()
                ? processor.getState().getViewPoint()
                : new Message("You have to load the map first! Load a map!")
        );
    }
}
