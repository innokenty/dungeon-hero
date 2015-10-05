package ru.innokenty.dungeonhero;

import ru.innokenty.dungeonhero.controller.Processor;
import ru.innokenty.dungeonhero.model.State;
import ru.innokenty.dungeonhero.view.Help;
import ru.innokenty.dungeonhero.view.Output;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class DungeonHero {

    private final Processor processor;
    private final CommandIterator commandIterator;
    private final Output output;

    public DungeonHero(State state, CommandIterator commandIterator, Output output) {
        this.processor = new Processor(state);
        this.commandIterator = commandIterator;
        this.output = output;
    }

    public void start() {
        output.output(Help.getInstance());
        commandIterator.forEachRemaining(processor, output);
    }
}
