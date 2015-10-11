package ru.innokenty.dungeonhero.controller.command;

import static ru.innokenty.dungeonhero.model.Skill.VISION;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class IncreaseVisionCommand extends LevelUpCommand {

    public IncreaseVisionCommand() {
        super('v', VISION);
    }
}
