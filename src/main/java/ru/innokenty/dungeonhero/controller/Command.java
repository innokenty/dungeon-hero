package ru.innokenty.dungeonhero.controller;

import ru.innokenty.dungeonhero.model.Skill;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * TODO transform to class hierarchy
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public enum Command {
    HELP('h', "help", "display this help"),
    INFO('i', "info", "display character stats and info"),
    MAP('m', "map", "display the currently visible portion of the map"),
    MOVE_UP('w', "up", "move character up if applicable"),
    MOVE_RIGHT('d', "right", "move character right if applicable"),
    MOVE_DOWN('s', "down", "move character down if applicable"),
    MOVE_LEFT('a', "left", "move character left if applicable"),
    PUNCH('p', "punch", "hit the monster while fighting"),
    FIGHT('f', "fight info", "display the current fight info"),
    STRENGTH('z', "+1 strength", "when gaining a new level you will be prompted which skill you want to upgrade"),
    AGILITY('x', "+1 agility", "when gaining a new level you will be prompted which skill you want to upgrade"),
    HEALTH('c', "+1 health", "when gaining a new level you will be prompted which skill you want to upgrade"),
    VISION('v', "+1 vision", "when gaining a new level you will be prompted which skill you want to upgrade"),
    SAVE('k', "save", "save the game to a file"),
    LOAD('l', "load", "open previously saved game from a file"),
    QUIT('q', "quit", "quit the game without saving");

    public final String key;
    public final String name;
    public final String description;

    Command(char key, String name, String description) {
        this.description = description;
        this.key = String.valueOf(key);
        this.name = name;
    }

    public static Command parse(String commandString) throws UnsupportedCommandException {
        return Arrays.asList(Command.values()).stream()
                     .filter(c -> c.key.equals(commandString) || c.name.equals(commandString))
                     .findFirst()
                     .orElseThrow(() -> new UnsupportedCommandException(commandString));
    }

    public static void forEach(Consumer<Command> action) {
        Arrays.asList(values()).stream().forEach(action);
    }

    public static Skill toSkill(Command command) {
        switch (command) {
            case STRENGTH: return Skill.STRENGTH;
            case AGILITY:  return Skill.AGILITY;
            case HEALTH:   return Skill.HEALTH;
            case VISION:   return Skill.VISION;
            default:
                throw new IllegalArgumentException("Only a skill up command is supported");
        }
    }
}
