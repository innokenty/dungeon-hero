package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.controller.Command;
import ru.innokenty.dungeonhero.view.Help;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class HelpPrinter implements Printer<Help> {

    private static final String HELP_MESSAGE = buildHelpMessage();

    private static final HelpPrinter instance = new HelpPrinter();

    public static HelpPrinter getInstance() {
        return instance;
    }

    private HelpPrinter() {
    }

    @Override
    public String stringify(Help help) {
        return HELP_MESSAGE;
    }

    private static String buildHelpMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append("To play type in one or multiple command codes and press enter.\n\n")
               .append("The following commands are available:\n");
        Command.forEach(command -> builder
               .append(command.key).append(", ")
               .append(command.name).append(" - ")
               .append(command.description)
               .append("\n"));
        builder.append("\n")
               .append("The map symbols denote the following:\n")
               .append("o       - you\n")
               .append("-+|     - walls\n")
               .append(".       - darkness, i.e. a cell that is out of vision\n")
               .append("@       - represents a portal, the map successful finish\n")
               .append("number  - a monster of the corresponding level\n");
        return builder.toString();
    }

}
