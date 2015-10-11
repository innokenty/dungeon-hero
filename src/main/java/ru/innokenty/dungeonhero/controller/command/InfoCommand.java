package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.controller.Processor;
import ru.innokenty.dungeonhero.model.Hero;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class InfoCommand extends Command {

    public InfoCommand() {
        super('i', "info", "display character stats and info");
    }

    public List<Hero> handle(Processor processor) {
        return singletonList(processor.getState().getHero());
    }
}
