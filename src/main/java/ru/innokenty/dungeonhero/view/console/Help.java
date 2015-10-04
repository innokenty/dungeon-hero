package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.controller.Command;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Help extends Message {

    private static final Help instance = new Help();

    public static Help getInstance() {
        return instance;
    }

    private Help() {
        super(helpMessage());
    }

    private static String helpMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append("Type in the command code or it's full name and press enter.\n")
               .append("The following commands are available:\n\n");
        Command.forEach(command -> builder.append(command.key).append(", ")
                                          .append(command.name).append(" - ")
                                          .append(command.description)
                                          .append("\n"));
        return builder.append("\n").toString();
    }
}
