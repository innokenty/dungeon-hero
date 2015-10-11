package ru.innokenty.dungeonhero.controller.command;

import ru.innokenty.dungeonhero.controller.Processor;

import java.util.Arrays;
import java.util.List;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public abstract class Command {

    public static final List<Command> COMMANDS = Arrays.asList(
            new FightCommand(),
            new HelpCommand(),
            new IncreaseAgilityCommand(),
            new IncreaseHealthCommand(),
            new IncreaseStrengthCommand(),
            new IncreaseVisionCommand(),
            new InfoCommand(),
            new LoadGameCommand(),
            new MapCommand(),
            new MoveDownCommand(),
            new MoveLeftCommand(),
            new MoveRightCommand(),
            new MoveUpCommand(),
            new PunchCommand(),
            new QuitCommand(),
            new SaveGameCommand()
    );

    private final String key;
    private final String name;
    private final String description;

    public Command(char key, String name, String description) {
        this.description = description;
        this.key = String.valueOf(key);
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract List<?> handle(Processor processor);
}
