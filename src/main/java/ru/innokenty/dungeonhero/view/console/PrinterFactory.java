package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.model.Hero;
import ru.innokenty.dungeonhero.model.ViewPoint;
import ru.innokenty.dungeonhero.view.Help;
import ru.innokenty.dungeonhero.view.Message;
import ru.innokenty.dungeonhero.view.Printable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class PrinterFactory {

    public static final Map<Class<? extends Printable>, Printer> PRINTER_MAP = new HashMap<>(2);

    static {
        PRINTER_MAP.put(Hero.class, HeroPrinter.getInstance());
        PRINTER_MAP.put(ViewPoint.class, ViewPointPrinter.getInstance());
        PRINTER_MAP.put(Message.class, MessagePrinter.getInstance());
        PRINTER_MAP.put(Help.class, HelpPrinter.getInstance());
    }

    private PrinterFactory() {
    }

    public static <T extends Printable> Printer<T> getPrinterFor(Printable printable) {
        //noinspection unchecked
        return Optional.ofNullable(PRINTER_MAP.get(printable.getClass()))
                       .orElseThrow(() -> new IllegalArgumentException(
                               "Can not create printer for " + printable.getClass().getSimpleName()));
    }
}
