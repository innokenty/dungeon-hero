package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.controller.command.UnsupportedCommandException;
import ru.innokenty.dungeonhero.view.Output;

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
    public void output(Object printable) {
        outStream.println(PrinterFactory.getPrinterFor(printable).print(printable));
    }

    @Override
    public void outputException(UnsupportedCommandException e) {
        errStream.println("Command '" + e.getCommand() + "' not supported!");
    }
}
