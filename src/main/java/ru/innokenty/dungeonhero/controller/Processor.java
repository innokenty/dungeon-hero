package ru.innokenty.dungeonhero.controller;

import ru.innokenty.dungeonhero.model.Cell;
import ru.innokenty.dungeonhero.model.Hero;
import ru.innokenty.dungeonhero.model.Monster;
import ru.innokenty.dungeonhero.model.MonsterCell;
import ru.innokenty.dungeonhero.model.Punch;
import ru.innokenty.dungeonhero.model.State;
import ru.innokenty.dungeonhero.model.ViewPoint;
import ru.innokenty.dungeonhero.view.Help;
import ru.innokenty.dungeonhero.view.Message;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static ru.innokenty.dungeonhero.controller.Command.toSkill;
import static ru.innokenty.dungeonhero.model.Cell.FINISH;

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
        this.finished = true;
    }

    public boolean hasFinished() {
        return finished;
    }

    public List<?> handle(Command command) {
        switch (command) {
            case MOVE_UP:
            case MOVE_RIGHT:
            case MOVE_DOWN:
            case MOVE_LEFT:
                return handleMove(command);
            case MAP:
                return singletonList(state.getViewPoint());
            case INFO:
                return singletonList(state.getHero());
            case FIGHT:
                return handleFightInfo();
            case PUNCH:
                return handlePunch(command);
            case STRENGTH:
            case AGILITY:
            case HEALTH:
            case VISION:
                return handleLevelUp(command);
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
                return handleQuit("Thank you, my hero, and good bye!");
            default:
                throw new IllegalArgumentException(format("Command '%s' is not supported!", command));
        }
    }

    private List<?> handleMove(Command command) {
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
        return singletonList(state.getViewPoint());
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

    private List<?> interact(Cell cell) {
        if (cell == FINISH) {
            return handleQuit("This is it! You've made it, my hero! Congrats, boy!");
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

    private List<?> handleFightInfo() {
        return state.isInFight()
               ? singletonList(state.getFight())
               : singletonList(new Message("Maaaan... Are you drunk? "
                       + "Whom are you fighting do you think? I'm your friend, chill out!"));
    }

    private List<?> handlePunch(Command command) {
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
            Hero hero = state.getHero();
            int experienceGained = fight.getExperience();
            hero.getExperience().gain(experienceGained);
            state.stopFight();
            state.getViewPoint().emptyCurrentCell();

            List<Object> output = new ArrayList<>(Arrays.asList(punch, new Message(format(
                    "Oh yeah, you've done it, you slaved the beast! You gain %d experience.",
                    experienceGained))));

            if (hero.getExperience().levelUp()) {
                state.startLevelUpPick();
                output.add(new Message(format(
                        "LEVEL UP! You're now on level %d. Pick a skill to increase! (see help for details)",
                        hero.getLevel())));
            } else {
                output.add(state.getViewPoint());
            }
            return output;
        } else {
            finish();
            return Arrays.asList(punch, new Message(
                    "Oh poor boy, you're dead now. Or as they said in the old times: GAME OVER!"));
        }
    }

    private List<?> handleLevelUp(Command command) {
        if (!state.isLevelUpPick()) {
            return singletonList(new Message("Haha, nice try! Why don't you fight someone "
                    + "instead of jerking around hm? Or I'll call for a REAL hero! How are feeling now?!"));
        }
        Hero hero = state.getHero();
        hero.up(toSkill(command));
        if (hero.getExperience().levelUp()) {
            return singletonList(new Message("And even one more level up! You're now on level " + hero.getLevel()));
        }
        state.finishLevelUpPick();
        return Arrays.asList(new Message("Ok, you're stronger now!"), hero);
    }

    private List<?> handleQuit(String message) {
        finish();
        return singletonList(new Message(message));
    }
}
