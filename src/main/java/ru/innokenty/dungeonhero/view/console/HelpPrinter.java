package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.controller.command.CommandFactory;
import ru.innokenty.dungeonhero.view.Help;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class HelpPrinter implements Printer<Help> {

    private static final String HELP_MESSAGE = buildHelpMessage();

    @Override
    public boolean accept(Object printable) {
        return printable instanceof Help;
    }

    @Override
    public String print(Help help) {
        return HELP_MESSAGE;
    }

    private static String buildHelpMessage() {
        StringBuilder builder = new StringBuilder();

        builder.append("To play type in one or multiple command codes and press enter.\n")
               .append("\n")
               .append("The following one-symbol commands are available:\n");

        CommandFactory.SIMPLE_COMMANDS.entrySet().stream().forEach(entry -> builder
               .append(entry.getKey()).append(", ")
               .append(entry.getValue().getName()).append(" - ")
               .append(entry.getValue().getDescription())
               .append("\n"));

        builder.append("\n")
               .append("The following commands require an argument that matches the following syntax:\n");

        CommandFactory.COMPLEX_COMMANDS.entrySet().stream().forEach(entry -> builder
               .append(entry.getKey()).append(", ")
               // TODO move command description to help class?
               .append(entry.getValue().apply(null).getName()).append(" - ")
               .append(entry.getValue().apply(null).getDescription())
               .append("\n"));

        builder.append("\n")
               .append("The map symbols denote the following:\n")
               .append("o        - you\n")
               .append("-+|      - walls\n")
               .append(".        - darkness, i.e. a cell that is out of vision\n")
               .append("@        - represents a portal, the map successful finish\n")
               .append("number   - a monster of the corresponding level\n")
               .append("\n")
               .append("The hero skills work as follows:\n")
               .append("health   - increases amount of hit points only\n")
               .append("strength - drastically increases maximum damage and gives some hit points\n")
               .append("agility  - increases both minimum and maximum damage and gives a bit of hit points\n")
               .append("vision   - increases vision range obviously, measured in cells\n")
               .append("\n")
               .append("To reach level N having level (N - 1) you need (N * 1000) exp\n")
               .append("You gain experience for each fight proportional to:\n")
               .append("1) slayed beast level\n")
               .append("2) division of the beast level by your level\n")
               .append("\n")
               .append("Tips:\n")
               .append("1) If you engage with a monster of a level higher than yours - you'll most likely die.\n")
               .append("2) If you engage with a monster of a level less than yours - almost no chance you'll die.\n")
               .append("3) If you die - you are `fucked` and you lose the game.\n")
               .append("4) It's easier to type multiple commands in and then press enter once.\n");

        return builder.toString();
    }
}
