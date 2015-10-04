package ru.innokenty.dungeonhero;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public interface CommandIterator {

    boolean hasNext();

    Command next() throws UnsupportedCommandException;

    default void forEachRemaining(Consumer<? super Command> action,
                                  Consumer<? super UnsupportedCommandException> onException) {
        Objects.requireNonNull(action);
        Objects.requireNonNull(onException);
        while (hasNext()) {
            try {
                action.accept(next());
            } catch (UnsupportedCommandException e) {
                onException.accept(e);
            }
        }
    }
}
