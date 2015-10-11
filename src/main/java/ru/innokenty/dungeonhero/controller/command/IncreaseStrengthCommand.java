package ru.innokenty.dungeonhero.controller.command;

import static ru.innokenty.dungeonhero.model.Skill.STRENGTH;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class IncreaseStrengthCommand extends LevelUpCommand {

    public IncreaseStrengthCommand() {
        super('z', STRENGTH);
    }
}
