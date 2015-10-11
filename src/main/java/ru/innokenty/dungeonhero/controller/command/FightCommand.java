package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.controller.Processor;
import ru.innokenty.dungeonhero.view.Message;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class FightCommand extends Command {

    public FightCommand() {
        super('f', "fight info", "display the current fight info");
    }

    @Override
    public List<?> handle(Processor processor) {
        return processor.getState().isInFight()
               ? singletonList(processor.getState().getFight())
               : singletonList(new Message("Maaaan... Are you drunk? "
                       + "Whom are you fighting do you think? I'm your friend, chill out!"));
    }
}
