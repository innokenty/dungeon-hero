package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.view.Printable;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Message implements Printable, Printer<Message> {

    private final String message;

    Message() {
        message = null;
    }

    public Message(String message) {
        this.message = message;
    }

    @Override
    public String stringify(Message message) {
        return message.message;
    }
}
