package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.controller.Processor;
import ru.innokenty.dungeonhero.model.Hero;
import ru.innokenty.dungeonhero.model.Skill;
import ru.innokenty.dungeonhero.view.Message;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public abstract class LevelUpCommand extends Command {

    private final Skill skill;

    public LevelUpCommand(char key, Skill skill) {
        this(key, skill, skill.name().toLowerCase());
    }

    public LevelUpCommand(char key, Skill skill, String skillName) {
        super("+1 " + skillName, "increase " + skillName + " when gaining a new level");
        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }

    @Override
    public List<?> handle(Processor processor) {
        if (!processor.getState().isLevelUpPick()) {
            return singletonList(new Message("Haha, nice try! Why don't you fight someone "
                    + "instead of jerking around, hm? Or I'll call for a REAL hero! How are feeling now?!"));
        }
        Hero hero = processor.getState().getHero();
        hero.up(getSkill());
        if (hero.getExperience().levelUp()) {
            return singletonList(new Message("And even one more level up! You're now on level " + hero.getLevel()));
        }
        processor.getState().finishLevelUpPick();
        return Arrays.asList(new Message("Ok, you're stronger now!"), hero);
    }
}
