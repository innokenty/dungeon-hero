package ru.innokenty.dungeonhero.controller;

import ru.innokenty.dungeonhero.model.Monster;
import ru.innokenty.dungeonhero.model.Punch;
import ru.innokenty.dungeonhero.model.State;
import ru.innokenty.dungeonhero.model.ViewPoint;
import ru.innokenty.dungeonhero.model.cell.Cell;
import ru.innokenty.dungeonhero.model.cell.EmptyCell;
import ru.innokenty.dungeonhero.model.cell.FinishCell;
import ru.innokenty.dungeonhero.model.cell.MonsterCell;
import ru.innokenty.dungeonhero.view.Help;
import ru.innokenty.dungeonhero.view.Message;
import ru.innokenty.dungeonhero.view.Printable;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.singletonList;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Processor {

    private final State state;

    private boolean finished;

    public Processor(State state) {
        this.state = state;
    }

    private void finish() {
        setFinished(true);
    }

    private void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean hasFinished() {
        return finished;
    }

    //TODO return single printable again, i.e. combine lists into new objects
    public List<Printable> handle(Command command) {
        switch (command) {
            case MOVE_UP:
            case MOVE_RIGHT:
            case MOVE_DOWN:
            case MOVE_LEFT:
                if (state.isInFight()) {
                    return singletonList(new Message(
                            "You were fighting, remember? You can't escape, not this time, no!"));
                }
                Cell cell = move(command);
                if (cell == null) {
                    return singletonList(new Message("Unable to move there, don't you see?!"));
                } else if (cell.isInteractable()) {
                    return interact(cell);
                }
            case MAP:
                return singletonList(state.getViewPoint());
            case INFO:
                return singletonList(state.getHero());
            case PUNCH:
                if (!state.isInFight()) {
                    return singletonList(new Message("Okay, you've punched some air in front of right on the face! "
                            + "Or balls! Or whatever... Good job for sure, now what?"));
                }

                FightProcessor fight = new FightProcessor(state.getFight());
                Punch punch = fight.hitOnce();
                if (!fight.isOver()) {
                    return singletonList(punch);
                }

                if (fight.heroWins()) {
                    state.stopFight();
                    state.getViewPoint().replaceCurrentCell(new EmptyCell(' '));
                    //TODO gain experience
                    return Arrays.asList(
                            punch,
                            new Message("Oh yeah, you've done it, you slaved the beast!"),
                            state.getViewPoint()
                    );
                } else {
                    finish();
                    return Arrays.asList(punch, new Message(
                            "Oh poor boy, you're dead now. Or as they said in the old times: GAME OVER!"));
                }
            case FIGHT:
                if (!state.isInFight()) {
                    return singletonList(new Message("Maaaan... Are you drunk? "
                            + "Whom are you fighting do you think? I'm your friend, chill out!"));
                }
                return singletonList(state.getFight());
            case SAVE:
                //TODO implement
                return singletonList(new Message("Successfully saved!"));
            case LOAD:
                //TODO implement
                return Arrays.asList(
                        new Message("Successfully loaded the saved game!"),
                        state.getViewPoint());
            case HELP:
                return singletonList(Help.getInstance());
            case QUIT:
                finish();
                return singletonList(new Message("Thank you, my hero, and good bye!"));
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

    private List<Printable> interact(Cell cell) {
        if (cell instanceof FinishCell) {
            finish();
            return singletonList(new Message("This is it! You've made it, my hero! Congrats, boy!"));
        }

        if (cell instanceof MonsterCell) {
            int level = ((MonsterCell) cell).getLevel();
            state.getHero().resetHealth();
            state.startFightWith(new Monster(level));
            return Arrays.asList(
                    new Message("You are now fighting with a savage beast! Oh, my brave hero, please be careful!\n"),
                    state.getFight())
                    ;
        }

        throw new IllegalArgumentException(format(
                "Interaction with cell of type %s is not yet implemented!",
                cell.getClass().getSimpleName()
        ));
    }
}
