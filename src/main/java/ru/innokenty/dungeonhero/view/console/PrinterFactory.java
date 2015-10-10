package ru.innokenty.dungeonhero.view.console;

import java.util.Arrays;
import java.util.List;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class PrinterFactory {

    private static final List<Printer<?>> PRINTERS = Arrays.asList(
            new HeroPrinter(),
            new ViewPointPrinter(),
            new MessagePrinter(),
            new HelpPrinter(),
            new FightPrinter(),
            new PunchPrinter()
    );

    private PrinterFactory() {
    }

    public static <T> Printer<T> getPrinterFor(Object printable) {
        //noinspection unchecked
        return (Printer<T>) PRINTERS.stream()
                .filter(printer -> printer.accept(printable))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Can not create printer for " + printable.getClass().getSimpleName()));
    }
}
