package ru.innokenty.dungeonhero.controller.command;

import static ru.innokenty.dungeonhero.model.Skill.AGILITY;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class IncreaseAgilityCommand extends LevelUpCommand {

    public IncreaseAgilityCommand() {
        super('x', AGILITY);
    }
}
