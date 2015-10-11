package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.controller.Processor;
import ru.innokenty.dungeonhero.view.Help;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class HelpCommand extends Command {

    public HelpCommand() {
        super('h', "help", "display this help");
    }

    @Override
    public List<Help> handle(Processor processor) {
        return singletonList(Help.getInstance());
    }
}
