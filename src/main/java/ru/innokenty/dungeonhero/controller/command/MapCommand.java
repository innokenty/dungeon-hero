package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.controller.Processor;
import ru.innokenty.dungeonhero.model.ViewPoint;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class MapCommand extends Command {

    public MapCommand() {
        super('m', "map", "display the currently visible portion of the map");
    }

    @Override
    public List<ViewPoint> handle(Processor processor) {
        return singletonList(processor.getState().getViewPoint());
    }
}
