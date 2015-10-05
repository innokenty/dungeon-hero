package ru.innokenty.dungeonhero.controller;

import ru.innokenty.dungeonhero.model.State;
import ru.innokenty.dungeonhero.model.ViewPoint;
import ru.innokenty.dungeonhero.model.cell.Cell;
import ru.innokenty.dungeonhero.model.cell.FinishCell;
import ru.innokenty.dungeonhero.view.Printable;
import ru.innokenty.dungeonhero.view.console.Help;
import ru.innokenty.dungeonhero.view.console.Message;

import java.awt.Point;

import static java.lang.String.format;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Processor {

    private final State state;

    private boolean finished;

    public Processor(State state) {
        this.state = state;
    }

    public void finish() {
        finished = true;
    }

    public boolean hasFinished() {
        return finished;
    }

    public Printable handle(Command command) {
        switch (command) {
            case MOVE_UP:
            case MOVE_RIGHT:
            case MOVE_DOWN:
            case MOVE_LEFT:
                Cell cell = move(command);
                if (cell == null) {
                    return new Message("Unable to move there, don't you see?!");
                } else if (cell.isInteractable()) {
                    return interact(cell);
                }
            case MAP:
                return state.getViewPoint();
            case INFO:
                return state.getHero();
            case SAVE:
                //TODO implement
                return new Message("Successfully saved!");
            case LOAD:
                //TODO implement
                return state.getViewPoint();
            case HELP:
                return Help.getInstance();
            case QUIT:
                finish();
                return new Message("Thank you, my hero, and good bye!");
            default:
                throw new IllegalArgumentException(format("Command '%s' is not supported!", command));
        }
    }

    public Cell move(Command moveCommand) {
        ViewPoint viewPoint = state.getViewPoint();
        Point dest = viewPoint.getLocation();
        switch (moveCommand) {
            case MOVE_UP:
                dest.y--;
                break;
            case MOVE_RIGHT:
                dest.x++;
                break;
            case MOVE_DOWN:
                dest.y++;
                break;
            case MOVE_LEFT:
                dest.x--;
                break;
        }

        if (viewPoint.getMap().isAccessible(dest)) {
            viewPoint.setLocation(dest);
            return viewPoint.getMap().getCell(dest);
        }

        return null;
    }

    private Printable interact(Cell cell) {
        if (cell instanceof FinishCell) {
            finish();
            return new Message("This is it! You've made it, my hero! Congrats, boy!");
        }

        throw new IllegalArgumentException(format(
                "Interaction with cell of type %s is not yet implemented!",
                cell.getClass().getSimpleName()));
    }
}
