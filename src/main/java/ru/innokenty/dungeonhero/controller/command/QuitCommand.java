package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.controller.Processor;
import ru.innokenty.dungeonhero.view.Message;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class QuitCommand extends Command {

    public QuitCommand() {
        super('q', "quit", "quit the game without saving");
    }

    @Override
    public List<Message> handle(Processor processor) {
        processor.finish();
        return singletonList(new Message("Thank you, my hero, and good bye!"));
    }
}
