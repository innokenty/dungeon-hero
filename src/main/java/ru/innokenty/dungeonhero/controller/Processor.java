package ru.innokenty.dungeonhero.controller;

import ru.innokenty.dungeonhero.controller.command.UnsupportedCommandException;
import ru.innokenty.dungeonhero.input.CommandIterator;
import ru.innokenty.dungeonhero.model.State;
import ru.innokenty.dungeonhero.view.Output;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Processor {

    private final CommandIterator commandIterator;
    private final Output output;

    private State state;

    private boolean finished;

    public Processor(State state, CommandIterator commandIterator, Output output) {
        this.state = state;
        this.commandIterator = commandIterator;
        this.output = output;
    }

    public void finish() {
        this.finished = true;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void start() {
        while (commandIterator.hasNext()) {
            try {
                commandIterator.next().handle(this).stream().forEach(output::output);
                if (finished) {
                    break;
                }
            } catch (UnsupportedCommandException e) {
                output.outputException(e);
            }
        }
    }
}
