package ru.innokenty.dungeonhero;

import ru.innokenty.dungeonhero.controller.Command;
import ru.innokenty.dungeonhero.controller.Processor;
import ru.innokenty.dungeonhero.controller.UnsupportedCommandException;
import ru.innokenty.dungeonhero.view.Output;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public interface CommandIterator {

    boolean hasNext();

    Command next() throws UnsupportedCommandException;

    default void forEachRemaining(Processor processor, Output output) {
        while (hasNext()) {
            try {
                output.output(processor.handle(next()));
                if (processor.hasFinished()) {
                    break;
                }
            } catch (UnsupportedCommandException e) {
                output.outputException(e);
            }
        }
    }
}
