package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.model.Skill;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public abstract class LevelUpCommand extends Command {

    private final Skill skill;

    public LevelUpCommand(char key, Skill skill) {
        this(key, skill, skill.name().toLowerCase());
    }

    public LevelUpCommand(char key, Skill skill, String skillName) {
        super(key, "+1 " + skillName, "increase " + skillName + " when gaining a new level");
        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }
}
