package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.view.Message;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class MessagePrinter implements Printer<Message> {

    @Override
    public boolean accept(Object printable) {
        return printable instanceof Message;
    }

    @Override
    public String print(Message message) {
        return message.getMessage();
    }
}
