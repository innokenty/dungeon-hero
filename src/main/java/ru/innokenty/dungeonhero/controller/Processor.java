package ru.innokenty.dungeonhero.controller;

import ru.innokenty.dungeonhero.controller.command.Command;
import ru.innokenty.dungeonhero.model.State;

import java.util.List;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Processor {

    public static final String FILENAME = "doc/saves/saved_game.dhs";

    private State state;

    private boolean finished;

    public Processor(State state) {
        this.state = state;
    }

    public void finish() {
        this.finished = true;
    }

    public boolean hasFinished() {
        return finished;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<?> handle(Command command) {
        return command.handle(this);
    }
}
