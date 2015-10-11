package ru.innokenty.dungeonhero.controller;

import ru.innokenty.dungeonhero.DungeonHeroException;
import ru.innokenty.dungeonhero.controller.command.*;
import ru.innokenty.dungeonhero.model.Cell;
import ru.innokenty.dungeonhero.model.Cell.Finish;
import ru.innokenty.dungeonhero.model.Hero;
import ru.innokenty.dungeonhero.model.Monster;
import ru.innokenty.dungeonhero.model.MonsterCell;
import ru.innokenty.dungeonhero.model.Punch;
import ru.innokenty.dungeonhero.model.State;
import ru.innokenty.dungeonhero.model.ViewPoint;
import ru.innokenty.dungeonhero.view.Help;
import ru.innokenty.dungeonhero.view.Message;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.singletonList;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Processor {

    private static final String FILENAME = "doc/saves/saved_game.dhs";

    private State state;

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
        if (command instanceof MoveCommand) {
            return handleMove((MoveCommand) command);
        } else if (command instanceof MapCommand) {
            return singletonList(state.getViewPoint());
        } else if (command instanceof InfoCommand) {
            return singletonList(state.getHero());
        } else if (command instanceof FightCommand) {
            return handleFightInfo((FightCommand) command);
        } else if (command instanceof PunchCommand) {
            return handlePunch((PunchCommand) command);
        } else if (command instanceof LevelUpCommand) {
            return handleLevelUp((LevelUpCommand) command);
        } else if (command instanceof SaveGameCommand) {
            return handleSave((SaveGameCommand) command);
        } else if (command instanceof LoadGameCommand) {
            return handleLoad((LoadGameCommand) command);
        } else if (command instanceof HelpCommand) {
            return singletonList(Help.getInstance());
        } else if (command instanceof QuitCommand) {
            return handleQuit((QuitCommand) command);
        }
        throw new IllegalArgumentException(format("Command '%s' is not supported!", command));
    }

    private List<?> handleMove(MoveCommand command) {
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

    public Cell move(MoveCommand moveCommand) {
        ViewPoint viewPoint = state.getViewPoint();
        Point dest = moveCommand.apply(viewPoint.getLocation());

        if (viewPoint.getMap().isAccessible(dest)) {
            viewPoint.setLocation(dest);
            return viewPoint.getMap().getCell(dest);
        }

        return null;
    }

    private List<?> interact(Cell cell) {
        if (cell instanceof Finish) {
            finish();
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

    @SuppressWarnings("UnusedParameters")
    private List<?> handleFightInfo(FightCommand command) {
        return state.isInFight()
               ? singletonList(state.getFight())
               : singletonList(new Message("Maaaan... Are you drunk? "
                       + "Whom are you fighting do you think? I'm your friend, chill out!"));
    }

    @SuppressWarnings("UnusedParameters")
    private List<?> handlePunch(PunchCommand command) {
        if (!state.isInFight()) {
            return singletonList(new Message("Okay, you've punched some air in front of right on the face! "
                    + "Or balls! Or whatever... Good job for sure, now what?"));
        }

        FightProcessor fight = new FightProcessor(state.getFight(), state.getDamageModel());
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

    private List<?> handleLevelUp(LevelUpCommand command) {
        if (!state.isLevelUpPick()) {
            return singletonList(new Message("Haha, nice try! Why don't you fight someone "
                    + "instead of jerking around, hm? Or I'll call for a REAL hero! How are feeling now?!"));
        }
        Hero hero = state.getHero();
        hero.up(command.getSkill());
        if (hero.getExperience().levelUp()) {
            return singletonList(new Message("And even one more level up! You're now on level " + hero.getLevel()));
        }
        state.finishLevelUpPick();
        return Arrays.asList(new Message("Ok, you're stronger now!"), hero);
    }

    @SuppressWarnings("UnusedParameters")
    private List<?> handleSave(SaveGameCommand command) {
        File file = new File(FILENAME);
        try {
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                throw new DungeonHeroException(format(
                        "Unable to create directory at %s!", file.getParentFile().getAbsolutePath()));
            }
            if (file.exists() && !file.canWrite()) {
                throw new DungeonHeroException(format(
                        "Unable to write to the file at %s!", file.getAbsolutePath()));
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(state);
            }
            return singletonList(new Message(format(
                    "Successfully saved game to file %s!", file.getAbsolutePath())));
        } catch (IOException e) {
            throw new DungeonHeroException(format(
                    "Unable to save the game to file %s!", file.getAbsolutePath()), e);
        }
    }

    @SuppressWarnings("UnusedParameters")
    private List<?> handleLoad(LoadGameCommand command) {
        File file = new File(FILENAME);
        try {
            if (!file.exists() || !file.canRead()) {
                throw new DungeonHeroException(format(
                        "Unable read the file at %s! Does it exist?", file.getAbsolutePath()));
            }
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                state = (State) ois.readObject();
            }
            return Arrays.asList(
                    new Message(format("Successfully loaded the saved game from %s!", file.getAbsolutePath())),
                    state.getViewPoint());
        } catch (ClassNotFoundException e) {
            throw new DungeonHeroException("Unable read the save file " + file.getAbsolutePath(), e);
        } catch (IOException e) {
            throw new DungeonHeroException("Unable to save the game to file " + file.getAbsolutePath(), e);
        }
    }

    @SuppressWarnings("UnusedParameters")
    private List<?> handleQuit(QuitCommand command) {
        finish();
        return singletonList(new Message("Thank you, my hero, and good bye!"));
    }
}
