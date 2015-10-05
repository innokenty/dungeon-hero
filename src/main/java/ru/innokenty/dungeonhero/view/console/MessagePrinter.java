package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.view.Message;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class MessagePrinter implements Printer<Message> {

    private static final MessagePrinter instance = new MessagePrinter();

    public static MessagePrinter getInstance() {
        return instance;
    }

    private MessagePrinter() {
    }
    
    @Override
    public String stringify(Message message) {
        return message.getMessage();
    }
}
