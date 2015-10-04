package ru.innokenty.dungeonhero.controller;

import ru.innokenty.dungeonhero.model.State;
import ru.innokenty.dungeonhero.model.ViewPoint;
import ru.innokenty.dungeonhero.view.Printable;
import ru.innokenty.dungeonhero.view.console.Help;
import ru.innokenty.dungeonhero.view.console.Message;

import java.awt.Point;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Processor {

    private final State state;

    public Processor(State state) {
        this.state = state;
    }

    public Printable handle(Command command) {
        switch (command) {
            case MOVE_UP:
            case MOVE_RIGHT:
            case MOVE_DOWN:
            case MOVE_LEFT:
                if (!move(state.getViewPoint(), command)) {
                    return new Message("Unable to move there, don't you see?!");
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
                return new Message("Thank you, my hero, and good bye!");
            default:
                throw new IllegalArgumentException("Command '" + command + "' is not supported!");
        }
    }

    public static boolean move(ViewPoint viewPoint, Command moveCommand) {
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
            default:
                return false;
        }

        if (viewPoint.getMap().isAccessible(dest)) {
            viewPoint.setLocation(dest);
            return true;
        }

        return false;
    }
}
