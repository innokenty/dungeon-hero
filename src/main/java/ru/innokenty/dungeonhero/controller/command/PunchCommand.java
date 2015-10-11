package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.controller.FightProcessor;
import ru.innokenty.dungeonhero.controller.Processor;
import ru.innokenty.dungeonhero.model.Hero;
import ru.innokenty.dungeonhero.model.Punch;
import ru.innokenty.dungeonhero.view.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.singletonList;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class PunchCommand extends Command {

    public PunchCommand() {
        super('p', "punch", "hit the monster while fighting");
    }

    @Override
    public List<?> handle(Processor processor) {
        if (!processor.getState().isInFight()) {
            return singletonList(new Message("Okay, you've punched some air in front of right on the face! "
                    + "Or balls! Or whatever... Good job for sure, now what?"));
        }

        FightProcessor fight = new FightProcessor(processor.getState().getFight(), processor.getState().getDamageModel());
        Punch punch = fight.hitOnce();
        if (!fight.isOver()) {
            return singletonList(punch);
        }

        if (fight.heroWins()) {
            Hero hero = processor.getState().getHero();
            int experienceGained = fight.getExperience();
            hero.getExperience().gain(experienceGained);
            processor.getState().stopFight();
            processor.getState().getViewPoint().emptyCurrentCell();

            List<Object> output = new ArrayList<>(Arrays.asList(punch, new Message(format(
                    "Oh yeah, you've done it, you slaved the beast! You gain %d experience.",
                    experienceGained))));

            if (hero.getExperience().levelUp()) {
                processor.getState().startLevelUpPick();
                output.add(new Message(format(
                        "LEVEL UP! You're now on level %d. Pick a skill to increase! (see help for details)",
                        hero.getLevel())));
            } else {
                output.add(processor.getState().getViewPoint());
            }
            return output;
        } else {
            processor.finish();
            return Arrays.asList(punch, new Message(
                    "Oh poor boy, you're dead now. Or as they said in the old times: GAME OVER!"));
        }
    }
}
