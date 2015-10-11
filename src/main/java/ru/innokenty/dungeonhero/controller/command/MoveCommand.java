package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.controller.Processor;
import ru.innokenty.dungeonhero.model.Cell;
import ru.innokenty.dungeonhero.model.Monster;
import ru.innokenty.dungeonhero.model.MonsterCell;
import ru.innokenty.dungeonhero.model.State;
import ru.innokenty.dungeonhero.model.ViewPoint;
import ru.innokenty.dungeonhero.view.Message;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.singletonList;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public abstract class MoveCommand extends Command {

    public MoveCommand(char key, String name, String description) {
        super(name, description);
    }

    protected abstract Point apply(Point point);

    @Override
    public List<?> handle(Processor processor) {
        State state = processor.getState();
        if (state.isInFight()) {
            return singletonList(new Message(
                    "You were fighting, remember? You can't escape, not this time, no!"));}
        Cell cell = move(state);
        if (cell == null) {
            return singletonList(new Message("Unable to move there, don't you see?!"));
        } else if (cell.isInteractable()) {
            return interact(cell, state, processor);
        }
        return singletonList(state.getViewPoint());
    }

    private Cell move(State state) {
        ViewPoint viewPoint = state.getViewPoint();
        Point dest = this.apply(viewPoint.getLocation());

        if (viewPoint.getMap().isAccessible(dest)) {
            viewPoint.setLocation(dest);
            return viewPoint.getMap().getCell(dest);
        }

        return null;
    }

    private List<?> interact(Cell cell, State state, Processor processor) {
        if (cell instanceof Cell.Finish) {
            processor.finish();
            return singletonList(new Message("This is it! You've made it, my hero! Congrats, boy!"));
        }

        if (cell instanceof MonsterCell) {
            int level = ((MonsterCell) cell).getLevel();
            state.getHero().resetHealth();
            state.startFightWith(new Monster(level));
            return Arrays.asList(
                    new Message("You are now fighting with a savage beast! Oh, my brave hero, please be careful!\n"),
                    state.getFight());
        }

        throw new IllegalArgumentException(format(
                "Interaction with cell of type %s is not yet implemented!",
                cell.getClass().getSimpleName()
        ));
    }
}
