package ru.innokenty.dungeonhero.controller.command;

import static ru.innokenty.dungeonhero.model.Skill.HEALTH;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class IncreaseHealthCommand extends LevelUpCommand {

    public IncreaseHealthCommand() {
        super('c', HEALTH);
    }
}
