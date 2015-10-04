package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.controller.UnsupportedCommandException;
import ru.innokenty.dungeonhero.view.Output;
import ru.innokenty.dungeonhero.view.Printable;

import java.io.PrintStream;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class PrintStreamOutput implements Output {

    private PrintStream outStream;
    private PrintStream errStream;

    public PrintStreamOutput(PrintStream outStream, PrintStream errStream) {
        this.outStream = outStream;
        this.errStream = errStream;
    }

    @Override
    public void output(Printable printable) {
        outStream.println(PrinterFactory.getPrinterFor(printable).stringify(printable));
    }

    @Override
    public void outputException(UnsupportedCommandException e) {
        errStream.println("Command '" + e.getCommand() + "' not supported!");
    }
}